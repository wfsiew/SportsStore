package sportsstore.domain;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("shippingDetailsValidator")
public class ShippingDetailsValidator implements Validator {
	private Locale locale;
	@Override
	public boolean supports(Class clazz) {
		return ShippingDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", rb.getString("shinfo.required.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "line1", "required.line1", rb.getString("shinfo.required.line1"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required.city", rb.getString("shinfo.required.city"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "required.state", rb.getString("shinfo.required.state"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "required.country", rb.getString("shinfo.required.country"));
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
