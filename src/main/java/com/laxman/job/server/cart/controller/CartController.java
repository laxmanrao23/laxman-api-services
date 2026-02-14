package com.laxman.job.server.cart.controller;

import com.laxman.job.server.cart.dto.*;
import com.laxman.job.server.cart.model.CartItem;
import com.laxman.job.server.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // React URL
public class CartController {

    private final CartService cartService;

    // 🔹 ADD TO CART
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItem>> addToCart(
            @RequestBody AddToCartRequest request) {

        CartItem item = cartService.addToCart(request);

        return ResponseEntity.ok(ApiResponse.success(item));
    }

    // 🔹 GET CART (summary with total)
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(
            @PathVariable String username) {

        CartResponse response = cartService.getCart(username);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 🔹 GET CART ITEMS (raw list)
    @GetMapping("/items/{username}")
    public ResponseEntity<ApiResponse<List<CartItem>>> getCartItems(
            @PathVariable String username) {

        List<CartItem> items = cartService.getCartByUsername(username);

        return ResponseEntity.ok(ApiResponse.success(items));
    }

    // 🔹 DELETE CART ITEM
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse<String>> deleteCartItem(
            @PathVariable Long cartItemId) {

        cartService.deleteCartItem(cartItemId);

        return ResponseEntity.ok(ApiResponse.success("Item removed successfully"));
    }

    // 🔹 UPDATE QUANTITY
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Void>> updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        cartService.updateQuantity(id, quantity);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
