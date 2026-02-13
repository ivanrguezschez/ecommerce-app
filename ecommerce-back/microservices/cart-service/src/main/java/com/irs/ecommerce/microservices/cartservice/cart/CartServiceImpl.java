package com.irs.ecommerce.microservices.cartservice.cart;

import com.irs.ecommerce.microservices.cartservice.exceptions.CartException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    @Override
    public CartResponse getCartByCustomerId(String customerId) {
        Cart cart = this.cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartException("Cart for customer with ID " + customerId + " does not exists"));

        return this.cartMapper.toCartResponse(cart);
    }

    @Override
    public void clearCart(String customerId) {
        Cart cart = this.cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartException("Cart for customer with ID " + customerId + " does not exists"));

        this.cartRepository.delete(cart);
    }
}
