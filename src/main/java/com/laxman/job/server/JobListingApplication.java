package com.laxman.job.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication

// 👉 JPA → ONLY CART (PostgreSQL)
//@EnableJpaRepositories(
//        basePackages = "com.laxman.job.server.cart.repository"
//)
//
//// 👉 MongoDB → USERS + PRODUCTS + JOBS
//@EnableMongoRepositories(
//        basePackages = {
//                "com.laxman.job.server.repository",              // users
//                "com.laxman.job.server.product.repository"       // products
//        }
//)

public class JobListingApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobListingApplication.class, args);
    }
}
