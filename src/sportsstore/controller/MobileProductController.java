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
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;

@Controller
@RequestMapping("/mobile/product")
public class MobileProductController {
	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getCategories(HttpServletRequest req, Model model) {
		List<String> categories = productService.getCategories();
		Cart cart = Cart.getCart(req);

		model.addAttribute("categories", categories);
		model.addAttribute("cart", cart);

		return "mobile/productspage";
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

	@RequestMapping(value = "/list/{pageNum}", method = RequestMethod.POST)
	public String getProductList(@PathVariable("pageNum") Integer pageNum,
			HttpServletRequest req, HttpServletResponse resp, Model model) {
		Map<String, Object> dic = null;
		String category = req.getParameter("category");
		String returnUrl = req.getParameter("returnUrl");
		
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");

		if (Utils.isEmptyString(category))
			dic = productService.getAll(pageNum, 10);

		else
			dic = productService.getByCategory(category, pageNum, 10);

		List<Product> products = (List<Product>) dic.get("list");

		model.addAttribute("products", products);
		model.addAttribute("returnUrl", returnUrl);

		return "mobile/productsummarylist";
	}

	@RequestMapping(value = "/{category}/{pageNum}", method = RequestMethod.GET)
	public String getProducts(@PathVariable("category") String category,
			@PathVariable("pageNum") Integer pageNum, HttpServletRequest req,
			Model model) {
		Map<String, Object> dic = null;

		if (Utils.isEmptyString(category))
			dic = productService.getAll(pageNum, 10);

		else
			dic = productService.getByCategory(category, pageNum, 10);

		List<Product> products = (List<Product>) dic.get("list");

		if (!Utils.isEmptyString(category))
			model.addAttribute("selectedCategory", category);

		String returnUrl = req.getRequestURI();
		req.getSession(true).setAttribute("returnUrl", returnUrl);

		model.addAttribute("products", products);
		model.addAttribute("returnUrl", returnUrl);

		return "mobile/productsummary";
	}
}
