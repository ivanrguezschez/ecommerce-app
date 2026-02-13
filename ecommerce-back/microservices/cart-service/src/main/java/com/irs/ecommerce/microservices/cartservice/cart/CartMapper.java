package com.irs.ecommerce.microservices.cartservice.cart;

import com.irs.ecommerce.microservices.cartservice.cartitem.CartItem;
import com.irs.ecommerce.microservices.cartservice.cartitem.CartItemResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    public CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> cartItemResponses = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            cartItemResponses.add(new CartItemResponse(item.getProductId(), item.getQuantity()));
        }

        return new CartResponse(cart.getId(), cart.getCustomerId(), cartItemResponses);
    }
}
