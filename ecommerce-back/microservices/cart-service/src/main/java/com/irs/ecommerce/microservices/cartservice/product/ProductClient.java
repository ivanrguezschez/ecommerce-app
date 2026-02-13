package com.irs.ecommerce.microservices.cartservice.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    Optional<ProductResponse> getProductById(@PathVariable Integer id);
}
