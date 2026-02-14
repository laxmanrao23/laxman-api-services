package com.laxman.job.server.cart.service;

import com.laxman.job.server.cart.dto.CartResponse;
import com.laxman.job.server.cart.dto.AddToCartRequest;
import com.laxman.job.server.cart.model.CartItem;
import java.util.*;



//Abstraction
public interface CartService {

    CartItem addToCart(AddToCartRequest request);

    CartResponse getCart(String username);

   // void removeItem(Long cartItemId);

    List<CartItem> getCartByUsername(String username);

    void deleteCartItem(Long id);

    void updateQuantity(Long id, int quantity);

}
