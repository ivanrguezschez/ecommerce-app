package com.irs.ecommerce.microservices.cartservice.cart;

public interface CartService {

    CartResponse getCartByCustomerId(String customerId);

    void clearCart(String customerId);
}
