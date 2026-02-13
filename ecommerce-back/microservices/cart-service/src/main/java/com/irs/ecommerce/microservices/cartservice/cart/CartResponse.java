package com.irs.ecommerce.microservices.cartservice.cart;

import com.irs.ecommerce.microservices.cartservice.cartitem.CartItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartResponse {

    private String id;

    private String customerId;

    private List<CartItemResponse> items;
}
