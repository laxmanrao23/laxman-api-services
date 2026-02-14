package com.laxman.job.server.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laxman.job.server.cart.model.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long>{

    List<CartItem> findByUsername(String username);
}
