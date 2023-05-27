package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
