package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.entity.Cart;
import net.javaguide.springboot.entity.CartItems;

public interface CartService {

	Cart addToCart(Cart cart);

	List<Cart> getCartItem();

	List<CartItems> getCartItemsByUserId();

	void deleteCartItemsByUserId();

	void deleteCartItemByProdId(int prodId);

}
