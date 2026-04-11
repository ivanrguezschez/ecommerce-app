package com.irs.ecommerce.microservices.productservice.product;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Obtiene todos los productos", description = "Recupera todos los productos.")
    @GetMapping()
    public List<ProductResponse> getProducts() {
        return this.productService.getProducts();
    }

    @Operation(summary = "Obtiene un producto específico", description = "Recupera un producto específico.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @Operation(summary = "Obtiene los productos para una categoria específica", description = "Recupera los productos de una categoria específica.")
    @GetMapping("/category/{id}")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable Integer id) {
        return this.productService.getProductsByCategoryId(id);
    }

    @Operation(summary = "Crea un nuevo producto", description = "Inserta un nuevo producto.")
    @PostMapping()
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(this.productService.createProduct(product));
    }

    @Operation(summary = "Modifica un producto específico", description = "Actualiza un producto específico.")
    @PutMapping()
    public ResponseEntity<Integer> updateProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(this.productService.updateProduct(product));
    }

    @Operation(summary = "Elimina un producto específico", description = "Borra un producto específico.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Compra una lista de productos ", description = "Realiza la compra de una serie de productos.")
    @PostMapping("/purchase")
    public ResponseEntity<Void> purchaseProduct(@Valid @RequestBody List<ProductQuantityRequest> request) {
        this.productService.purchaseProduct(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Repone el stock de una lista de productos ", description = "Realiza la reposición del stock de una serie de productos.")
    @PostMapping("/restock")
    public ResponseEntity<Void> updateProductStock(@Valid @RequestBody List<ProductQuantityRequest> request) {
        this.productService.restockProduct(request);
        return ResponseEntity.ok().build();
    }

}
