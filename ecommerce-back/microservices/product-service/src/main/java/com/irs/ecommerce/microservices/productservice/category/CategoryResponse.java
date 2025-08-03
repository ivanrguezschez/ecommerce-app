package com.irs.ecommerce.microservices.productservice.category;

import com.irs.ecommerce.microservices.productservice.product.ProductResponse;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        List<ProductResponse> products) {
}
