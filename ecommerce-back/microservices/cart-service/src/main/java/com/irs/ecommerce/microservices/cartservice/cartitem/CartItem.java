package com.irs.ecommerce.microservices.cartservice.cartitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItem {

    private Integer productId;

    private Integer quantity;
}
