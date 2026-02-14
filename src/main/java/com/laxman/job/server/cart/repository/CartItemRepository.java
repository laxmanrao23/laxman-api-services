package com.laxman.job.server.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laxman.job.server.cart.model.CartItem;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUsername(String username);

    Optional<CartItem> findByUsernameAndProductId(String username, String productId);
}
