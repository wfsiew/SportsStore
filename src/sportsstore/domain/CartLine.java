package sportsstore.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class CartLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485038387665678594L;

	private Product product;
	private int quantity;

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
