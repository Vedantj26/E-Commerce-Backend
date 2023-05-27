package net.javaguide.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguide.springboot.entity.Products;
import net.javaguide.springboot.entity.ProductsDTO;

public interface ProductsRepository extends JpaRepository<Products, Long> {
	@Query("SELECT p FROM Products p WHERE " + "p.name LIKE CONCAT('%', :query, '%')"
			+ "Or p.description LIKE CONCAT('%', :query, '%')")
	List<Products> searchProduct(String query);

	List<Products> findByCatId(long catId);

	@Query("SELECT new net.javaguide.springboot.entity.ProductsDTO(p.id,  p.catId,  p.price,  p.isActive,  p.name,  p.description, "
			+ "p.imagePath,  p.update_date,  p.createdDate, c.id, c.qty) from Cart as c "
			+ "Inner Join Products as p on c.prodId=p.id " + "Inner Join User as u on c.userId = u.id "
			+ "WHERE p.id = :productId AND u.id = :userId")
	ProductsDTO getProductDetailsBycartId(long userId, long productId);

}
