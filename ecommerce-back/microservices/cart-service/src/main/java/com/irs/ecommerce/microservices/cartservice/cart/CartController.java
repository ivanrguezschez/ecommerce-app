package com.irs.ecommerce.microservices.cartservice.cart;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{customerId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Obtiene el carrito para un cliente específico", description = "Recupera el carrito de la compra del cliente específico.")
    @GetMapping()
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable("customerId") String customerId) {
        CartResponse cartResponse = this.cartService.getCartByCustomerId(customerId);

        return ResponseEntity.ok(cartResponse);
    }

    @Operation(summary = "Elimina el carrito para un cliente específico", description = "Borra el carrito de la compra del cliente específico.")
    @DeleteMapping()
    public ResponseEntity<Void> clearCart(@PathVariable("customerId") String customerId) {
        this.cartService.clearCart(customerId);

        return ResponseEntity.noContent().build();
    }
}
