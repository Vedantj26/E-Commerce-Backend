package net.javaguide.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartItems")
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long userId;
	private double price;
	private int qty;
	private String imagePath;
	private String catName;
	private double totalAmount;
	private long cartId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", name=" + name + ", userId=" + userId + ", price=" + price + ", qty=" + qty
				+ ", imagePath=" + imagePath + ", catName=" + catName + ", totalAmount=" + totalAmount + ", cartId="
				+ cartId + "]";
	}

	public CartItems(long id, String name, long userId, double price, int qty, String imagePath, String catName,
			double totalAmount, long cartId) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.price = price;
		this.qty = qty;
		this.imagePath = imagePath;
		this.catName = catName;
		this.totalAmount = totalAmount;
		this.cartId = cartId;
	}

	public CartItems() {
		super();
	}

}
