package net.javaguide.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.Cart;
import net.javaguide.springboot.entity.CartItems;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.repository.CartRepository;
import net.javaguide.springboot.service.CartService;
import net.javaguide.springboot.service.IAuthenticationFacade;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Override
	public Cart addToCart(Cart cart) {
		Cart addedItem = cartRepository.save(cart);
		return addedItem;
	}

	@Override
	public List<Cart> getCartItem() {
		List<Cart> cartList = cartRepository.findAll();
		return cartList;
	}

	@Override
	public List<CartItems> getCartItemsByUserId() {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		List<CartItems> cartitem = cartRepository.getCartItemsByUserId(dbUser.getId());
		return cartitem;
	}

	@Override
	public void deleteCartItemsByUserId() {
		Authentication auth = authenticationFacade.getAuthentication();
		User dbUser = (User) auth.getPrincipal();
		cartRepository.deleteCartItemsByUserId(dbUser.getId());
	}

	@Override
	public void deleteCartItemByProdId(int prodId) {
		cartRepository.deleteCartItemByProdId(prodId);
	}

}
