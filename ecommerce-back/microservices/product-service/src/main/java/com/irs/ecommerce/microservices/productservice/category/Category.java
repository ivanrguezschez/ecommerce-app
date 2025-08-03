package com.irs.ecommerce.microservices.productservice.category;

import com.irs.ecommerce.microservices.productservice.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    private List<Product> products;
}
