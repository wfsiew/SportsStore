package sportsstore.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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
@RequestMapping("/mobile")
public class MobileController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(Device device, Model model) {
		if (device.isMobile())
			model.addAttribute("device", "Mobile Device");
		
		else if (device.isNormal())
			model.addAttribute("device", "Normal Desktop");
		
		else if (device.isTablet())
			model.addAttribute("device", "Tablet Device");
		
		else
			model.addAttribute("device", "Unknown Device");
		
		return "mobile";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String sitePref(SitePreference sitePreference, Model model) {
		if (sitePreference == SitePreference.MOBILE)
			return "redirect:/mobile/product";
		
		else
			return "redirect:/product";
	}
}
