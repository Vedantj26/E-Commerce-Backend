package net.javaguide.springboot.entity;

import jakarta.persistence.Column;
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
@Table(name = "products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long catId;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private boolean isActive;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = true)
	private String imagePath;
	private String update_date;
	private String createdDate;

	public String getUpdate_date() {
		return update_date;
	}

	public String getCreatedDate() {
		return createdDate;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", catId=" + catId + ", price=" + price + ", isActive=" + isActive + ", name="
				+ name + ", description=" + description + ", imagePath=" + imagePath + ", update_date=" + update_date
				+ ", createdDate=" + createdDate + "]";
	}

	public Products() {
		super();
	}

	public Products(long id, long catId, double price, boolean isActive, String name, String description,
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
}
