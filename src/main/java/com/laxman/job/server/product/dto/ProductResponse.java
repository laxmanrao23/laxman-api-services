package com.laxman.job.server.product.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private Double price;
    private String category;
    private Integer quantity;
}
