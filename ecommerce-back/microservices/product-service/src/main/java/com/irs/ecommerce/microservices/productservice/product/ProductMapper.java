package com.irs.ecommerce.microservices.productservice.product;

import com.irs.ecommerce.microservices.productservice.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .imageUrl(request.imageUrl())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }
}
