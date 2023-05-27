package net.javaguide.springboot.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String mobile;
	private String address;
	private double totPrice;
	@CreationTimestamp
	private Date orderDate;
	@UpdateTimestamp
	private Date lastUpdatedDate;
	private long userId;
	private String status = "Pending";
	private String remark;
	@Nullable
	private String cancelledBy;
	
	

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderItemId", referencedColumnName = "id")
	private Set<OrderItem> orderItems = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order(int id, String name, String email, String mobile, String address, double totPrice, Date orderDate,
			Date lastUpdatedDate, long userId, String status, String remark, String cancelledBy,
			Set<OrderItem> orderItems) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.totPrice = totPrice;
		this.orderDate = orderDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.userId = userId;
		this.status = status;
		this.remark = remark;
		this.cancelledBy = cancelledBy;
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", address=" + address
				+ ", totPrice=" + totPrice + ", orderDate=" + orderDate + ", lastUpdatedDate=" + lastUpdatedDate
				+ ", userId=" + userId + ", status=" + status + ", remark=" + remark + ", cancelledBy=" + cancelledBy
				+ ", orderItems=" + orderItems + "]";
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
