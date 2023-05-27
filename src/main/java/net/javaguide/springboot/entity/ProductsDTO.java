package net.javaguide.springboot.entity;

public class ProductsDTO {
	private long id;
	private long catId;
	private double price;
	private boolean isActive;
	private String name;
	private String description;
	private String imagePath;
	private String update_date;
	private String createdDate;
	private long cartId;
	private int qty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "ProductsDTO [id=" + id + ", catId=" + catId + ", price=" + price + ", isActive=" + isActive + ", name="
				+ name + ", description=" + description + ", imagePath=" + imagePath + ", update_date=" + update_date
				+ ", createdDate=" + createdDate + ", cartId=" + cartId + ", qty=" + qty + "]";
	}

	public ProductsDTO(long id, long catId, double price, boolean isActive, String name, String description,
			String imagePath, String update_date, String createdDate, long cartId, int qty) {
		super();
		this.id = id;
		this.catId = catId;
		this.price = price;
		this.isActive = isActive;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.update_date = update_date;
		this.createdDate = createdDate;
		this.cartId = cartId;
		this.qty = qty;
	}

	public ProductsDTO(long id, long catId, double price, boolean isActive, String name, String description,
			String imagePath, String update_date, String createdDate) {
		super();
		this.id = id;
		this.catId = catId;
		this.price = price;
		this.isActive = isActive;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.update_date = update_date;
		this.createdDate = createdDate;
	}

	public ProductsDTO() {
		super();
	}

}
