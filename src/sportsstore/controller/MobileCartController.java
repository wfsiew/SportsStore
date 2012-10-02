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
@RequestMapping("/mobile/cart")
public class MobileCartController {
	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "orderService")
	private OrderService orderService;

	@Autowired
	private CartValidator cartValidator;

	@Autowired
	private ShippingDetailsValidator shippingDetailsValidator;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String carts(HttpServletRequest req, Model model) {
		Cart cart = Cart.getCart(req);
		
		String returnUrl = (String) req.getSession(false).getAttribute("returnUrl");
		
		if (Utils.isEmptyString(returnUrl))
			returnUrl = "/mobile/product";
		
		model.addAttribute("cart", cart);
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("formatter", new sportsstore.Formatter());
		
		return "mobile/cartpage";
	}
	
	@RequestMapping(value = "/add/{productID}", method = RequestMethod.GET)
	public String addToCart(@PathVariable("productID") Integer productID,
			HttpServletRequest req, Model model) {
		Product product = productService.getProduct(productID);

		if (product != null) {
			Cart.getCart(req).addItem(product, 1);
		}

		return carts(req, model);
	}
	
	@RequestMapping(value = "/remove/{productID}", method = RequestMethod.GET)
	public String removeFromCart(@PathVariable("productID") Integer productID,
			HttpServletRequest req, Model model) {
		Product product = productService.getProduct(productID);

		if (product != null) {
			Cart.getCart(req).removeLine(product);
		}

		return carts(req, model);
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(HttpServletRequest req, Model model) {
		List<String> categories = productService.getCategories();
		String theme = (String) req.getSession().getAttribute("theme");

		model.addAttribute("shippingDetailsAttribute", new ShippingDetails());
		model.addAttribute("formatter", new sportsstore.Formatter());
		model.addAttribute("theme", theme);

		return "mobile/checkoutpage";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(
			@ModelAttribute("shippingDetailsAttribute") ShippingDetails shippingDetails,
			BindingResult result, Locale locale, HttpServletRequest req, Model model) {
		Cart cart = Cart.getCart(req);
		
		cartValidator.setLocale(locale);
		shippingDetailsValidator.setLocale(locale);
		cartValidator.validate(cart, result);
		shippingDetailsValidator.validate(shippingDetails, result);
		
		if (result.hasErrors()) {
			String cartempty = cartValidator.getCartEmptyError(result);
			if (cartempty != null) {
				model.addAttribute("cartempty", cartempty);
			}
			
			return "mobile/checkoutpage";
		}
		
		orderService.setLocale(locale);
		orderService.processOrder(cart, shippingDetails);
		cart.clear();
		
		return "mobile/checkoutcomplete";
	}
}
