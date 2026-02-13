package com.irs.ecommerce.microservices.cartservice.cart;

import com.irs.ecommerce.microservices.cartservice.cartitem.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    private String customerId;

    private List<CartItem> items;

}
