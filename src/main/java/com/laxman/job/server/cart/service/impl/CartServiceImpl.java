package com.laxman.job.server.cart.service.impl;

import com.laxman.job.server.cart.dto.AddToCartRequest;
import com.laxman.job.server.cart.dto.CartItemResponse;
import com.laxman.job.server.cart.dto.CartResponse;
import com.laxman.job.server.cart.model.CartItem;
import com.laxman.job.server.cart.repository.CartItemRepository;
import com.laxman.job.server.cart.service.CartService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CartServiceImpl implements CartService {

    private static final Logger log =
            LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartItemRepository repository;

    @Override
    public CartItem addToCart(AddToCartRequest request) {
        try {

            int qty = request.getQuntity() != null ? request.getQuntity() : 1;
            double price = request.getPrice() != null ? request.getPrice() : 0.0;

            CartItem item = repository
                    .findByUsernameAndProductId(
                            request.getUsername(),
                            request.getProductid()
                    )
                    .orElse(
                            CartItem.builder()
                                    .username(request.getUsername())
                                    .productId(request.getProductid())
                                    .productName(request.getProductName())
                                    .price(price)
                                    .quantity(0)
                                    .build()
                    );

            item.setQuantity(item.getQuantity() + qty);

            return repository.save(item);

        } catch (DataIntegrityViolationException ex) {
            log.error("DB constraint failed while adding to cart. Request={}", request, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error in addToCart. Request={}", request, ex);
            throw ex;
        }
    }


    @Override
    public CartResponse getCart(String username) {
        List<CartItem> items = repository.findByUsername(username);

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        return CartResponse.builder()
                .items(
                        items.stream()
                                .map(i -> new CartItemResponse(
                                        i.getId(),
                                        i.getProductName(),
                                        i.getPrice(),
                                        i.getQuantity()
                                ))
                                .toList()
                )
                .totalAmount(total)
                .build();
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        repository.deleteById(cartItemId);
    }

    @Override
    public List<CartItem> getCartByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void updateQuantity(Long id, int quantity) {
        CartItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity <= 0) {
            repository.deleteById(id);
        } else {
            item.setQuantity(quantity);
            repository.save(item);
        }
    }

}
