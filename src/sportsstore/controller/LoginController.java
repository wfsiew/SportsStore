package sportsstore.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/admin/loginfailed", method = RequestMethod.GET)
	public String loginError(Locale locale, Model model) {
		ResourceBundle rb = ResourceBundle.getBundle("admin", locale);
		String msg = rb.getString("login.error");
		model.addAttribute("error", true);
		model.addAttribute("errormsg", msg);
		
		return "login";
	}
	
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "login";
	}
}
