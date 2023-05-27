package net.javaguide.springboot.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.Products;
import net.javaguide.springboot.entity.ProductsDTO;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.RecordNotFoundException;
import net.javaguide.springboot.repository.PaginationRepository;
import net.javaguide.springboot.repository.ProductsRepository;
import net.javaguide.springboot.service.IAuthenticationFacade;
import net.javaguide.springboot.service.ProductsService;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private PaginationRepository paginationRepository;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Override
	public Products createProduct(Products products) {
		return productsRepository.save(products);
	}

	@Override
	public Products getProductsById(long prodId) throws RecordNotFoundException {
		Optional<Products> optionalProduct = productsRepository.findById(prodId);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			throw new RecordNotFoundException("User not Found with id " + prodId);
		}
	}

	@Override
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}

	@Override
	public Products updateProducts(Products products, String filecode) {
		Products existingProducts = productsRepository.findById(products.getId()).get();

		LocalDateTime ldt = LocalDateTime.now();
		String Date = DateTimeFormatter.ofPattern("yyyy-MM-dd.hh-MM-ss").format(ldt);

		existingProducts.setUpdate_date(Date);
		existingProducts.setCatId(products.getCatId());
		existingProducts.setName(products.getName());
		existingProducts.setImagePath(filecode);
		existingProducts.setDescription(products.getDescription());
		existingProducts.setPrice(products.getPrice());
		Products updateProducts = productsRepository.save(existingProducts);
		return updateProducts;
	}

	@Override
	public void deleteProducts(long productId) {
		productsRepository.deleteById(productId);
	}

	@Override
	public void deleteAllProducts() {
		productsRepository.deleteAll();

	}

	@Override
	public List<Products> searchProduct(String query) {
		return productsRepository.searchProduct(query);
	}

	@Override
	public List<Products> getProductsByCatId(long catId) {
		List<Products> prodList = productsRepository.findByCatId(catId);
		return prodList;
	}

	@Override
	public Object getProductDetailsBycartId(long productId) {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		ProductsDTO prod = productsRepository.getProductDetailsBycartId(dbUser.getId(), productId);
		if (prod == null) {
			Optional<Products> product = productsRepository.findById(productId);
			return product;
		} else {
			return prod;
		}
	}

	@Override
	public Object paginatedProd(int pageNo, int pagSize) {
		Pageable page = PageRequest.of(pageNo, pagSize);
		Page<Products> pageList = paginationRepository.findAll(page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Message", "Fetch Product Successfully");
		map.put("status", 200);
		map.put("data", pageList.getContent());
		map.put("totalPage", pageList.getTotalPages());
		return map;
	}

}
