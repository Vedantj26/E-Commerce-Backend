package net.javaguide.springboot.model;

import java.util.Date;

public class FilterDTO {

	private String userId;
	private String orderStatus;
	private String orderDate;
	private String customerName;
	private Date orderDateStart;
	private Date orderDateEnd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getOrderDateStart() {
		return orderDateStart;
	}
	public void setOrderDateStart(Date orderDateStart) {
		this.orderDateStart = orderDateStart;
	}
	public Date getOrderDateEnd() {
		return orderDateEnd;
	}
	public void setOrderDateEnd(Date orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}
	@Override
	public String toString() {
		return "FilterDTO [userId=" + userId + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", customerName=" + customerName + ", orderDateStart=" + orderDateStart + ", orderDateEnd="
				+ orderDateEnd + "]";
	}
	public FilterDTO(String userId, String orderStatus, String orderDate, String customerName, Date orderDateStart,
			Date orderDateEnd) {
		super();
		this.userId = userId;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.orderDateStart = orderDateStart;
		this.orderDateEnd = orderDateEnd;
	}
	public FilterDTO() {
		super();
	}

	

}
