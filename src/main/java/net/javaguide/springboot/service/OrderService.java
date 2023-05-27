package net.javaguide.springboot.service;

import java.util.Date;
import java.util.List;

import net.javaguide.springboot.entity.Order;
import net.javaguide.springboot.exception.RecordNotFoundException;
import net.javaguide.springboot.model.FilterDTO;

public interface OrderService {

	Order save(Order order);

	List<Order> orderList() throws RecordNotFoundException;

	List<Order> orderByUserId() throws RecordNotFoundException;
	
	Order orderById(long id);
	
	Order orderStatus(Order order);
	
	List<Order> filterOrder(String orderDate, String status, String userId);

	List<Order> filterByOrderDateBetween(Date orderDateStartTime, Date orderDateEndTime);
}
