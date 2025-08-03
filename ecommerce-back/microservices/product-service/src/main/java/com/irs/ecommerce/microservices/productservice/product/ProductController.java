package com.irs.ecommerce.microservices.productservice.product;

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

    @GetMapping()
    public List<ProductResponse> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @GetMapping("/category/{id}")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable Integer id) {
        return this.productService.getProductsByCategoryId(id);
    }

    @PostMapping()
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(this.productService.createProduct(product));
    }

    @PutMapping()
    public ResponseEntity<Integer> updateProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(this.productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Void> purchaseProduct(@Valid @RequestBody List<ProductQuantityRequest> request) {
        this.productService.purchaseProduct(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restock")
    public ResponseEntity<Void> updateProductStock(@Valid @RequestBody List<ProductQuantityRequest> request) {
        this.productService.restockProduct(request);
        return ResponseEntity.ok().build();
    }

}
