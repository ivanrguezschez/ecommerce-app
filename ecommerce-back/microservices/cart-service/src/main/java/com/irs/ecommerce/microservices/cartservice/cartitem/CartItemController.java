package com.irs.ecommerce.microservices.cartservice.cartitem;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{customerId}/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @Operation(summary = "Añade un producto específico al carrito de un cliente específico", description = "Inserta un producto específico al carrito de la compra de un cliente específico.")
    @PostMapping()
    public ResponseEntity<String> addItemToCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok(this.cartItemService.addItemToCart(customerId, cartItemRequest));
    }

    @Operation(summary = "Modifica un producto específico del carrito de un cliente específico", description = "Actualiza un producto específico del carrito de la compra de un cliente específico.")
    @PutMapping()
    public ResponseEntity<Void> updateItemFromCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        this.cartItemService.updateItemFromCart(customerId, cartItemRequest);

        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Elimina un producto específico del carrito de un cliente específico", description = "Borra un producto específico del carrito de la compra de un cliente específico.")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable("customerId") String customerId, @PathVariable("productId") Integer productId) {
        this.cartItemService.removeItemFromCart(customerId, productId);

        return ResponseEntity.accepted().build();
    }
}
