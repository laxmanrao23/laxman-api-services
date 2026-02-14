package com.laxman.job.server.cart.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import lombok.Getter;
import lombok.Builder;
import java.util.List;

@Builder
@Getter
public class CartResponse {
    private List<CartItemResponse> items;
    private double totalAmount;


}
