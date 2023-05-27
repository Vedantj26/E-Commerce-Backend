package net.javaguide.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import net.javaguide.springboot.entity.Order;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.RecordNotFoundException;
import net.javaguide.springboot.model.FilterDTO;
import net.javaguide.springboot.repository.OrderRepository;
import net.javaguide.springboot.service.IAuthenticationFacade;
import net.javaguide.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public Order save(Order order) {
//		// Creating a simple mail message
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//		// Setting up necessary details
//		mailMessage.setFrom(sender);
//		mailMessage.setTo(order.getEmail());
//		mailMessage.setText("Hi, " + order.getName() + " Your Order is Successfully Placed and "
//				+ "It will de Delivered on your Address which is " + order.getAddress()
//				+ ". You will get Call from our delivery person on your Mobile No " + order.getMobile()
//				+ ". Total Order Items in your List is " + order.getOrderItems()
//				+ " and Total Amount you have to Pay for your Order is ₹ " + order.getTotPrice());
//		mailMessage.setSubject(order.getName() + " Your Order is Placed.");
//
//		// Sending the mail
//		javaMailSender.send(mailMessage);
		Order o = orderRepository.save(order);

		return o;
	}

	@Override
	public List<Order> orderList() throws RecordNotFoundException {
		List<Order> orderList = orderRepository.findAll(Sort.by(Direction.DESC, "lastUpdatedDate"));
		return orderList;
	}

	@Override
	public List<Order> orderByUserId() throws RecordNotFoundException {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		List<Order> orderList = orderRepository.findByUserIdOrderByOrderDateDesc(dbUser.getId());
		return orderList;
	}

//	Invoice
	@Override
	public Order orderById(long id) {
		Order order = orderRepository.findById(id);
		return order;
	}

	@Override
	public Order orderStatus(Order order) {
		Order o = orderRepository.findById(order.getId());
		o.setStatus(order.getStatus());
		o.setRemark(order.getRemark());
		o.setCancelledBy(order.getCancelledBy());
		Order newOrder = orderRepository.save(o);
		return newOrder;
	}

	@Override
	public List<Order> filterOrder(String orderDate, String status, String userId) {
		List<Order> list = orderRepository.findByStatus(orderDate, status, userId);
		return list;
	}
	
	@Override
	public List<Order> filterByOrderDateBetween(Date orderDateStartTime, Date orderDateEndTime)
	{
		List<Order> orderList = orderRepository.findAllByOrderDateBetween(orderDateStartTime, orderDateEndTime);
		return orderList;
	}

}
