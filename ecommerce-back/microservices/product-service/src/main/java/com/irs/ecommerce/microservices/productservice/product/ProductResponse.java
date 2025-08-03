package com.irs.ecommerce.microservices.productservice.product;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer categoryId,
        String categoryName,
        String categoryDescription) {
}
