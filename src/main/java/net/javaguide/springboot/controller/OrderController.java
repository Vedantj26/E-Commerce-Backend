package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.EmailDTO;
import net.javaguide.springboot.entity.Order;
import net.javaguide.springboot.model.FilterDTO;
import net.javaguide.springboot.model.ResponseHandler;
import net.javaguide.springboot.service.EmailService;
import net.javaguide.springboot.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("api/")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private EmailService emailService;

	@PostMapping("orders/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody Order order) {
		Order savedData = orderService.save(order);
		return ResponseHandler.generateResponse("Order placed successfully", HttpStatus.OK, savedData);

	}
	
	@PutMapping("orders/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody Order order) {
		Order savedData = orderService.orderStatus(order);
		return ResponseHandler.generateResponse("Order Updated successfully", HttpStatus.OK, savedData);

	}

	@GetMapping("orders/getAllOrder")
	public ResponseEntity<?> getAllOrder() {
		List<Order> orderList = orderService.orderList();
		return ResponseHandler.generateResponse("Order fetched successfully", HttpStatus.OK, orderList);

	}

	@GetMapping("orders/getOrderByUserId/{userId}")
	public ResponseEntity<?> getOrderByUserId() {

		List<Order> orderList = orderService.orderByUserId();
		return ResponseHandler.generateResponse("Order fetched successfully", HttpStatus.OK, orderList);

	}

//		Invoice
	@GetMapping("orders/getOrderById/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") long id) {

		Order order = orderService.orderById(id);
		return ResponseHandler.generateResponse("Order fetched successfully", HttpStatus.OK, order);

	}

	// Sending a simple Email
	@PostMapping("orders/sendMail")
	public String sendMail(@RequestBody EmailDTO emailDTO) {
		String status = emailService.sendEmail(emailDTO);

		return status;
	}

	// Sending email with attachment
	@PostMapping("orders/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDTO emailDTO) {
		String status = emailService.sendEmailWithAttach(emailDTO);

		return status;
	}
	
	// Filter Orders By Status
	@PostMapping("orders/filterStatus/")
	public ResponseEntity<?> filterOrders(@RequestBody FilterDTO filterDto)
	{
		if(filterDto.getOrderDateEnd() == null){
			List<Order> orderList = orderService.filterOrder(filterDto.getOrderDate(), filterDto.getOrderStatus(), filterDto.getUserId());
			return ResponseHandler.generateResponse("Filtered by Status", HttpStatus.OK, orderList);
		}else {
			List<Order> orderList = orderService.filterByOrderDateBetween(filterDto.getOrderDateStart(),filterDto.getOrderDateEnd());
			return ResponseHandler.generateResponse("Filtered by Order Date Range", HttpStatus.OK, orderList);
		}
	}
}
