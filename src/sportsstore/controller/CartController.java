package sportsstore.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import sportsstore.*;
import sportsstore.domain.*;
import sportsstore.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "orderService")
	private OrderService orderService;

	@Autowired
	private CartValidator cartValidator;

	@Autowired
	private ShippingDetailsValidator shippingDetailsValidator;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String carts(
			@RequestParam(value = "returnUrl", required = false, defaultValue = "") String returnUrl,
			HttpServletRequest req, Model model) {
		Cart cart = Cart.getCart(req);
		List<String> categories = productService.getCategories();

		model.addAttribute("cart", cart);
		model.addAttribute("categories", categories);
		model.addAttribute("returnUrl", req.getContextPath() + returnUrl);
		model.addAttribute("formatter", new sportsstore.Formatter());

		return "cartpage";
	}

	@RequestMapping(value = "/add/{productID}", method = RequestMethod.POST)
	public String addToCart(@PathVariable("productID") Integer productID,
			HttpServletRequest req, Model model) {
		String url = req.getParameter("returnUrl");
		Product product = productService.getProduct(productID);

		if (product != null) {
			Cart.getCart(req).addItem(product, 1);
		}

		String u = Utils.getURI(req, url);
		UriComponents redirectUri = UriComponentsBuilder
				.fromPath("/cart").queryParam("returnUrl", u).build()
				.encode();

		return String.format("redirect:%s", redirectUri.toUriString());
	}

	@RequestMapping(value = "/remove/{productID}", method = RequestMethod.POST)
	public String removeFromCart(@PathVariable("productID") Integer productID,
			HttpServletRequest req, Model model) {
		String url = req.getParameter("returnUrl");
		Product product = productService.getProduct(productID);

		if (product != null) {
			Cart.getCart(req).removeLine(product);
		}

		String u = Utils.getURI(req, url);
		UriComponents redirectUri = UriComponentsBuilder
				.fromPath("/cart").queryParam("returnUrl", u).build()
				.encode();

		return String.format("redirect:%s", redirectUri.toUriString());
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(HttpServletRequest req, Model model) {
		Cart cart = Cart.getCart(req);
		List<String> categories = productService.getCategories();

		model.addAttribute("cart", cart);
		model.addAttribute("categories", categories);
		model.addAttribute("shippingDetailsAttribute", new ShippingDetails());
		model.addAttribute("formatter", new sportsstore.Formatter());

		return "checkoutpage";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(
			@ModelAttribute("shippingDetailsAttribute") ShippingDetails shippingDetails,
			BindingResult result, Locale locale, HttpServletRequest req, Model model) {
		Cart cart = Cart.getCart(req);
		List<String> categories = productService.getCategories();
		
		cartValidator.setLocale(locale);
		shippingDetailsValidator.setLocale(locale);
		cartValidator.validate(cart, result);
		shippingDetailsValidator.validate(shippingDetails, result);

		model.addAttribute("cart", cart);
		model.addAttribute("categories", categories);
		model.addAttribute("formatter", new sportsstore.Formatter());

		if (result.hasErrors()) {
			String cartempty = cartValidator.getCartEmptyError(result);
			if (cartempty != null) {
				model.addAttribute("cartempty", cartempty);
			}
			
			return "checkoutpage";
		}

		orderService.setLocale(locale);
		orderService.processOrder(cart, shippingDetails);
		cart.clear();

		return "checkoutcomplete";
	}
}
