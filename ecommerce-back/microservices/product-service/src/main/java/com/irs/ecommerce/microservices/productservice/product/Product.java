package com.irs.ecommerce.microservices.productservice.product;

import com.irs.ecommerce.microservices.productservice.category.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    private Double price;

    private Integer stock  ;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "category_id")
    private Category category;

    @PrePersist
    public void prePersist() {
        if (stock == null) {
            stock = 0;
        }
    }
}
