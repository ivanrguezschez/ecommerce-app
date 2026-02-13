package com.irs.ecommerce.microservices.cartservice.cartitem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{customerId}/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping()
    public ResponseEntity<String> addItemToCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok(this.cartItemService.addItemToCart(customerId, cartItemRequest));
    }

    @PutMapping()
    public ResponseEntity<Void> updateItemFromCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        this.cartItemService.updateItemFromCart(customerId, cartItemRequest);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable("customerId") String customerId, @PathVariable("productId") Integer productId) {
        this.cartItemService.removeItemFromCart(customerId, productId);

        return ResponseEntity.accepted().build();
    }
}
