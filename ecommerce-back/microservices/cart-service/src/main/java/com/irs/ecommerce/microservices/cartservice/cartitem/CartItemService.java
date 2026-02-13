package com.irs.ecommerce.microservices.cartservice.cartitem;

import jakarta.validation.Valid;

public interface CartItemService {
    String addItemToCart(String customerId, CartItemRequest cartItemRequest);

    void updateItemFromCart(String customerId, @Valid CartItemRequest cartItemRequest);

    void removeItemFromCart(String customerId, Integer productId);
}
