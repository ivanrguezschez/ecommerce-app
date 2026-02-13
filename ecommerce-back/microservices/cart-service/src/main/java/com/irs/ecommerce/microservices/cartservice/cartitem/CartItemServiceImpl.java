package com.irs.ecommerce.microservices.cartservice.cartitem;

import com.irs.ecommerce.microservices.cartservice.cart.Cart;
import com.irs.ecommerce.microservices.cartservice.cart.CartRepository;
import com.irs.ecommerce.microservices.cartservice.customer.CustomerClient;
import com.irs.ecommerce.microservices.cartservice.customer.CustomerResponseDTO;
import com.irs.ecommerce.microservices.cartservice.exceptions.CartException;
import com.irs.ecommerce.microservices.cartservice.product.ProductClient;
import com.irs.ecommerce.microservices.cartservice.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    @Override
    public String addItemToCart(String customerId, CartItemRequest cartItemRequest) {
        CustomerResponseDTO customerResponseDTO = this.customerClient.getCustomerById(customerId)
                .orElseThrow(() -> new CartException("Customer with ID " + customerId + " does not exist"));

        ProductResponse productResponse = this.productClient.getProductById(cartItemRequest.productId())
                .orElseThrow(() -> new CartException("Product with ID " + cartItemRequest.productId() + " does not exist"));

        if (productResponse.stock() < cartItemRequest.quantity()) {
            throw new CartException("Product with ID " + cartItemRequest.productId() + " does not have enough stock");
        }

        Cart cart = this.cartRepository.findByCustomerId(customerResponseDTO.id())
                .orElse(Cart.builder()
                        .customerId(customerId)
                        .items(new ArrayList<>())
                        .build()
                );

        boolean productExists = cart.getItems().stream().anyMatch(item -> item.getProductId().equals(cartItemRequest.productId()));
        if (productExists) {
            throw new CartException("Product with ID " + cartItemRequest.productId() + " is already in the cart");
        }

        cart.getItems().add(
                CartItem.builder()
                    .productId(cartItemRequest.productId())
                    .quantity(cartItemRequest.quantity())
                    .build()
        );

        this.cartRepository.save(cart);

        return cart.getId();
    }

    public void updateItemFromCart(String customerId, CartItemRequest cartItemRequest) {
        Cart cart = this.cartRepository.findByCustomerId(customerId)
            .orElseThrow(() -> new CartException("Cart for customer with ID " + customerId + " does not exist"));

        CartItem itemToUpdate = cart.getItems().stream()
                .filter(item -> item.getProductId().intValue() == cartItemRequest.productId().intValue())
                .findFirst()
                .orElseThrow(() -> new CartException("Product with ID " + cartItemRequest.productId() + " is not in the cart"));
        if (this.productClient.getProductById(cartItemRequest.productId()).get().stock() < cartItemRequest.quantity()) {
            throw new CartException("Product with ID " + cartItemRequest.productId() + " does not have enough stock");
        }

        itemToUpdate.setQuantity(cartItemRequest.quantity());
                this.cartRepository.save(cart);
    }

    public void removeItemFromCart(String customerId, Integer productId) {
        Cart cart = this.cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartException("Cart for customer with ID " + customerId + " does not exist"));

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProductId().intValue() == productId.intValue())
                .findFirst()
                .orElseThrow(() -> new CartException("Product with ID " + productId + " is not in the cart"));;

        cart.getItems().remove(itemToRemove);

        this.cartRepository.save(cart);
    }
}
