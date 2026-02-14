package com.laxman.job.server.product.service;

import com.laxman.job.server.product.dto.ProductRequest;
import com.laxman.job.server.product.dto.ProductResponse;
import java.util.*;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);
}
