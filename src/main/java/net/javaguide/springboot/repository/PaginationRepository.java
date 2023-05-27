package net.javaguide.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.javaguide.springboot.entity.Products;

public interface PaginationRepository extends PagingAndSortingRepository<Products, Long> {

}
