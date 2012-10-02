package sportsstore.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class Cart implements Serializable {
	private List<CartLine> lineCollection = new ArrayList<CartLine>();

	public void addItem(Product product, int quantity) {
		CartLine line = null;

		for (CartLine o : lineCollection) {
			if (o.getProduct().getProductID() == product.getProductID()) {
				line = o;
				break;
			}
		}

		if (line == null) {
			CartLine o = new CartLine();
			o.setProduct(product);
			o.setQuantity(quantity);
			lineCollection.add(o);
		}

		else {
			line.setQuantity(line.getQuantity() + quantity);
		}
	}

	public void removeLine(Product product) {
		List<Product> l = new ArrayList<Product>();
		for (int i = 0; i < lineCollection.size(); i++) {
			Product p = lineCollection.get(i).getProduct();
			if (p.getProductID() == product.getProductID()) {
				lineCollection.remove(i);
				--i;
			}
		}
	}

	public double computeTotalValue() {
		double sum = 0;
		try {
			for (CartLine o : lineCollection) {
				sum += o.getProduct().getPrice() * o.getQuantity();
			}
		}

		catch (Exception e) {
		}

		return sum;
	}
	
	public int getTotalQuantity() {
		int total = 0;
		for (CartLine o : lineCollection) {
			total += o.getQuantity();
		}
		
		return total;
	}

	public void clear() {
		lineCollection.clear();
	}

	public List<CartLine> getLines() {
		return lineCollection;
	}
	
	public static Cart getCart(HttpServletRequest req) {
		Cart cart = (Cart) req.getSession(true).getAttribute("Cart");
		if (cart == null) {
			cart = new Cart();
			req.getSession(true).setAttribute("Cart", cart);
		}

		return cart;
	}
}
