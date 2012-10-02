package sportsstore.service;

import java.util.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sportsstore.*;
import sportsstore.Formatter;
import sportsstore.domain.*;

@Service("orderService")
public class OrderService {
	@Autowired
	private MailSender mailSender;

	@Resource(name = "velocityEngine")
	private VelocityEngine velocityEngine;
	
	private Locale locale;

	public void processOrder(Cart cart, ShippingDetails shippingInfo) {
//		StringBuilder body = new StringBuilder();
//		body.append("A new order has been submitted\n");
//		body.append("---\n");
//		body.append("Items:\n");
//
//		for (CartLine line : cart.getLines()) {
//			double subtotal = line.getProduct().getPrice() * line.getQuantity();
//			String f = String.format("%d x %s (subtotal: %.2f)\n",
//					line.getQuantity(), line.getProduct().getName(), subtotal);
//			body.append(f);
//		}
//
//		String r = String.format("Total order value: %.2f\n",
//				cart.computeTotalValue());
//		body.append(r);
//		body.append("---\n");
//		body.append("Ship to:\n");
//		body.append(shippingInfo.getName() + "\n");
//		body.append(shippingInfo.getLine1() + "\n");
//		body.append(shippingInfo.getLine2() == null ? "\n" : shippingInfo
//				.getLine2() + "\n");
//		body.append(shippingInfo.getLine3() == null ? "\n" : shippingInfo
//				.getLine3() + "\n");
//		body.append(shippingInfo.getCity() + "\n");
//		body.append(shippingInfo.getState() == null ? "\n" : shippingInfo
//				.getState() + "\n");
//		body.append(shippingInfo.getCountry() + "\n");
//		body.append(shippingInfo.getZip() + "\n");
//		body.append("---\n");
//		r = String.format("Gift wrap: %s", shippingInfo.isGiftwrap() ? "Yes"
//				: "No");
//		body.append(r);
		
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		sportsstore.Formatter formatter = new Formatter(locale);

		Map<String, Object> dic = new HashMap<String, Object>();
		dic.put("cart", cart);
		dic.put("shinfo", shippingInfo);
		dic.put("formatter", formatter);
		dic.put("rb", rb);

		String bodyplain = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"sportsstore/service/order.txt", "UTF-8", dic);
		String bodyhtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"sportsstore/service/order.html", "UTF-8", dic);

		MimeMessage message = mailSender.createMimeMessage();	

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo("wfsiew@localhost");
			helper.setFrom(mailSender.getFrom());
			helper.setSubject(rb.getString("order.subject"));
			helper.setText(bodyplain, bodyhtml);
			mailSender.send(message);
		}

		catch (MessagingException e) {
			System.err.println(e.getMessage());
		}
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
