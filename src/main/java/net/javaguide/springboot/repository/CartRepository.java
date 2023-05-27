package net.javaguide.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import net.javaguide.springboot.entity.Cart;
import net.javaguide.springboot.entity.CartItems;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("SELECT new net.javaguide.springboot.entity.CartItems(p.id, p.name, c.userId, p.price, c.qty, p.imagePath, cat.catName, (p.price*c.qty), c.id) from Cart as c "
			+ "Inner Join Products as p on c.prodId=p.id " + "Inner Join User as u on c.userId = u.id "
			+ "Inner join Category as cat on p.catId = cat.id " + "WHERE u.id = :userId")
	public List<CartItems> getCartItemsByUserId(long userId);

	@Modifying
	@Transactional
//	@Query(value = "DELETE FROM Cart c WHERE c.user_id = :user_id")
	public void deleteCartItemsByUserId(long userId);

	@Transactional
	public void deleteCartItemByProdId(long prodId);

}
