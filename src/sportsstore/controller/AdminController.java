package sportsstore.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sportsstore.*;
import sportsstore.domain.*;
import sportsstore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productadmin")
public class AdminController {
	@Resource(name = "productService")
	private ProductService productService;

	@Autowired
	private ProductValidator productValidator;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getProducts(HttpServletRequest req, Model model) {
		Map<String, Object> dic = productService.getAll(1, 0);
		List<Product> products = (List<Product>) dic.get("list");

		String message = (String) model.asMap().get("message");
		try {
			if (message == null) {
				HttpSession o = req.getSession();
				if (o != null) {
					message = (String) o.getAttribute("message");
					o.setAttribute("message", null);
				}
			}
		}

		catch (Exception e) {
			message = null;
		}

		model.addAttribute("products", products);
		model.addAttribute("formatter", new sportsstore.Formatter());
		model.addAttribute("message", message);

		return "productsadminpage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreate(Model model) {
		model.addAttribute("productAttribute", new Product());

		return "addproductpage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveCreate(
			@ModelAttribute("productAttribute") Product product,
			BindingResult result, Locale locale, HttpServletRequest req,
			Model model) {
		productValidator.setLocale(locale);
		productValidator.validate(product, result);

		if (result.hasErrors())
			return "addproductpage";

		productService.addProduct(product);

		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		String msg = String.format(rb.getString("product.save_success"),
				product.getName());
		
		req.getSession(true).setAttribute("message", msg);

		return "redirect:/productadmin";
	}

	@RequestMapping(value = "/edit/{productID}", method = RequestMethod.GET)
	public String getEdit(@PathVariable("productID") Integer productID,
			Model model) {
		model.addAttribute("productAttribute",
				productService.getProduct(productID));

		return "editproductpage";
	}

	@RequestMapping(value = "/edit/{productID}", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("productAttribute") Product product,
			BindingResult result, @PathVariable("productID") Integer productID,
			Locale locale, HttpServletRequest req, Model model) {
		productValidator.setLocale(locale);
		productValidator.validate(product, result);

		if (result.hasErrors())
			return "editproductpage";

		product.setProductID(productID);

		productService.editProduct(product);

		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		String msg = String.format(rb.getString("product.save_success"),
				product.getName());

		req.getSession(true).setAttribute("message", msg);

		return "redirect:/productadmin";
	}

	@RequestMapping(value = "/getimage/{productID}", method = RequestMethod.GET)
	public @ResponseBody
	byte[] getImage(@PathVariable("productID") Integer productID, Model model) {
		try {
			Product p = productService.getProduct(productID);
			return p.getImageData();
		}

		catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/delete/{productID}", method = RequestMethod.GET)
	public String delete(@PathVariable("productID") Integer productID,
			Locale locale, HttpServletRequest req, Model model) {
		Product p = productService.delete(productID);

		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		String msg = String.format(rb.getString("product.delete_success"),
				p.getName());

		req.getSession(true).setAttribute("message", msg);

		return "redirect:/productadmin";
	}
}
