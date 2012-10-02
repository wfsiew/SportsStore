package sportsstore.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "product")
@Access(AccessType.PROPERTY)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3697213959854136668L;

	private int productID;
	private String name;
	private String description;
	private Double price;
	private String category;
	private byte[] imageData;
	private String imageMimeType;
	private CommonsMultipartFile fileData;

	/**
	 * @return the productID
	 */
	@Id
	@Column(name = "productID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID
	 *            the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	@Column(name = "price", precision = 2)
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	@Column(name = "category", length = 50)
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the imageData
	 */
	@Column(name = "imagedata")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getImageData() {
		if (imageData != null && imageData.length > 0)
			return imageData;
		
		return null;
	}

	/**
	 * @param imageData
	 *            the imageData to set
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	/**
	 * @return the imageMimeType
	 */
	@Column(name = "imagemimetype", length = 50)
	public String getImageMimeType() {
		return imageMimeType;
	}

	/**
	 * @param imageMimeType
	 *            the imageMimeType to set
	 */
	public void setImageMimeType(String imageMimeType) {
		this.imageMimeType = imageMimeType;
	}

	/**
	 * @return the fileData
	 */
	@Transient
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	/**
	 * @param fileData
	 *            the fileData to set
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
		if (fileData != null) {
			setImageData(fileData.getBytes());
			setImageMimeType(fileData.getContentType());
		}
	}
}
