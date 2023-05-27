package net.javaguide.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguide.springboot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserIdOrderByOrderDateDesc(long userId);

	Order findById(long id);
	
	@Query(value ="SELECT o FROM Order o "
			+ "WHERE o.orderDate LIKE CONCAT('%', :orderDate, '%') "
			+ "AND o.status LIKE CONCAT('%', :status, '%') "
			+ "AND o.userId LIKE CONCAT('%', :userId, '%') "
			+ "AND o.name LIKE CONCAT('%', :userId, '%') ")
	List<Order> findByStatus(String orderDate, String status, String userId);
	
	List<Order> findAllByOrderDateBetween(Date orderDateStartTime, Date orderDateEndTime);

}
