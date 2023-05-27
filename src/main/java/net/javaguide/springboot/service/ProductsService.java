package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.entity.Products;

public interface ProductsService {
	Products createProduct(Products products);

	Products getProductsById(long prodId);

	List<Products> getAllProducts();

	List<Products> getProductsByCatId(long catId);

	Object getProductDetailsBycartId(long productId);

	Products updateProducts(Products products, String filecode);

	void deleteProducts(long productId);

	void deleteAllProducts();

	List<Products> searchProduct(String query);

	Object paginatedProd(int pageNo, int pagSize);
}
