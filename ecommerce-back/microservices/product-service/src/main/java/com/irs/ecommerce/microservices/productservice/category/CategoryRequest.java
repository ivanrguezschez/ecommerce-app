package com.irs.ecommerce.microservices.productservice.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "Category name is required")
        String name,
        String description) {
}
