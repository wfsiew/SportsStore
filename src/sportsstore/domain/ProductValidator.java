package sportsstore.domain;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("productValidator")
public class ProductValidator implements Validator {
	private Locale locale;
	
	@Override
	public boolean supports(Class clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", rb.getString("product.required.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", rb.getString("product.required.description"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required.price", rb.getString("product.required.price"));
		ValidationUtils.rejectIfEmpty(errors, "category", "required.category", rb.getString("product.required.category"));
		
		Product p = (Product) model;
		if (p.getPrice() != null && p.getPrice() < 0)
			errors.rejectValue("price", "invalidprice", rb.getString("product.invalidprice"));
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
