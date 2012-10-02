package sportsstore.domain;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.ObjectError;

@Component("cartValidator")
public class CartValidator implements Validator {
	private Locale locale;
	
	@Override
	public boolean supports(Class clazz) {
		return Cart.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		Cart cart = (Cart) model;
		if (cart.getLines().size() == 0) {
			ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
			errors.reject("cartempty", rb.getString("cart.empty"));
		}
	}
	
	public String getCartEmptyError(BindingResult result) {
		List<ObjectError> ls = result.getAllErrors();
		String r = null;
		for (ObjectError o : ls) {
			if (o.getCode().equals("cartempty")) {
				r = o.getDefaultMessage();
				break;
			}
		}
		
		return r;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
