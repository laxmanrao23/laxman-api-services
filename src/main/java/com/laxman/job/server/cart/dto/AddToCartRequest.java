package com.laxman.job.server.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    private String username;
    private String productid;
    private String productName;
    private Integer price;
    private Integer quntity;
}
