package com.laxman.job.server.product.repository;

import com.laxman.job.server.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String>{

    }
