package com.irs.ecommerce.microservices.cartservice.cartitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemResponse {

    private Integer productId;

    private Integer quantity;
}
