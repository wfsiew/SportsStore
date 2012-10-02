package sportsstore.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getProducts(HttpServletRequest req, Model model) {
		return getProducts("", 1, req, model);
	}

	@RequestMapping(value = "/index/{pageNum}", method = RequestMethod.GET)
	public String getProducts(
			@PathVariable("pageNum") Integer pageNum,
			@RequestParam(value = "category", required = false, defaultValue = "") String category,
			HttpServletRequest req, Model model) {
		return getProducts("", pageNum, req, model);
	}

	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public String getProducts(@PathVariable("category") String category,
			HttpServletRequest req, Model model) {
		return getProducts(category, 1, req, model);
	}

	@RequestMapping(value = "/{category}/{pageNum}", method = RequestMethod.GET)
	public String getProducts(@PathVariable("category") String category,
			@PathVariable("pageNum") Integer pageNum, HttpServletRequest req,
			Model model) {
		Map<String, Object> dic = null;
		Cart cart = Cart.getCart(req);
		List<String> categories = productService.getCategories();

		if (Utils.isEmptyString(category))
			dic = productService.getAll(pageNum, 4);

		else
			dic = productService.getByCategory(category, pageNum, 4);

		List<Product> products = (List<Product>) dic.get("list");
		Pager pager = (Pager) dic.get("pager");

		if (Utils.isEmptyString(category))
			pager.setLinkUrl("/product/index");

		else {
			pager.setLinkUrl(String.format("/product/%s", category));
			model.addAttribute("selectedCategory", category);
		}

		model.addAttribute("products", products);
		model.addAttribute("cart", cart);
		model.addAttribute("categories", categories);
		model.addAttribute("pageLinks", pager.getPageLinks());
		model.addAttribute("returnUrl", req.getRequestURI());
		model.addAttribute("formatter", new sportsstore.Formatter());

		return "productspage";
	}
}
