package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguide.springboot.entity.Cart;
import net.javaguide.springboot.entity.CartItems;
import net.javaguide.springboot.service.CartService;
import net.javaguide.springboot.service.IAuthenticationFacade;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("api/")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("cart/addToCart")
	public Cart addToCart(@RequestBody Cart cart) {
		return cartService.addToCart(cart);
	}

	@GetMapping("cart/getAllCartItems")
	public List<Cart> getAllCartItems() {
		return cartService.getCartItem();
	}

	@GetMapping("cart/getCartItemsByUserId/{userId}")
	public List<CartItems> getCartItemsByUserId() {
		List<CartItems> cartitem = cartService.getCartItemsByUserId();
		return cartitem;

	}

	@DeleteMapping("cart/deleteCartItemsByUserId/{id}")
	public ResponseEntity<String> deleteProductById() {
		cartService.deleteCartItemsByUserId();
		return new ResponseEntity<>("Cart Item successfully deleted!", HttpStatus.OK);
	}

	@DeleteMapping("cart/deleteCartItemByProdId/{id}")
	public ResponseEntity<String> deleteCartItemByProdId(@PathVariable("id") int prodId) {
		cartService.deleteCartItemByProdId(prodId);
		return new ResponseEntity<>("Cart Item successfully deleted!", HttpStatus.OK);

	}

}
