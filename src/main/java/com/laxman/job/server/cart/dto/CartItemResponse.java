package com.laxman.job.server.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemResponse {

    private Long id;
    private String productName;
    private double price;
    private int quantity;
}
