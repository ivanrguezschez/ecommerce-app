package com.irs.ecommerce.microservices.productservice.product;

import com.irs.ecommerce.microservices.productservice.category.CategoryService;
import com.irs.ecommerce.microservices.productservice.exceptions.CategoryException;
import com.irs.ecommerce.microservices.productservice.exceptions.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public List<ProductResponse> getProducts() {
        return this.productRepository.findAll().stream()
                .map(this.productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProductById(Integer id) {
        if (id == null) {
            throw new ProductException("Product ID cannot be null");
        }
        return this.productRepository.findById(id)
                .map(this.productMapper::toProductResponse)
                //.orElse(null);
                .orElseThrow(() -> new ProductException("Product with ID %s not found".formatted(id)));
    }

    public List<ProductResponse> getProductsByCategoryId(Integer id) {
        if (id == null) {
            throw new ProductException("Category ID cannot be null");
        }
        return this.productRepository.findAll().stream()
                .filter(product -> product.getCategory().getId().equals(id))
                .map(this.productMapper::toProductResponse)
                .toList();
    }

    public Integer createProduct(ProductRequest product) {
        if (this.categoryService.getCategoryById(product.categoryId()) == null) {
            throw new ProductException("Category with ID %s not found".formatted(product.categoryId()));
        }

        Product newProduct = this.productMapper.toProduct(product);
        Product savedProduct = this.productRepository.save(newProduct);

        return savedProduct.getId();
    }

    public Integer updateProduct(ProductRequest request) {
        if (request.id() == null) {
            throw new ProductException("Product ID cannot be null");
        }

        if (this.categoryService.getCategoryById(request.categoryId()) == null) {
            throw new ProductException("Category with ID %s not found".formatted(request.categoryId()));
        }

        Product existingProduct = this.productRepository.findById(request.id())
                .orElseThrow(() -> new ProductException("Product with ID %s not found".formatted(request.id())));

        Product updatedProduct = this.productMapper.toProduct(request);

        // Ensure we do not overwrite stock
        updatedProduct.setStock(existingProduct.getStock());

        this.productRepository.save(updatedProduct);

        return updatedProduct.getId();
    }

    public void deleteProduct(Integer id) {
        if (id == null) {
            throw new ProductException("Product ID cannot be null");
        }
        if (!this.productRepository.existsById(id)) {
            throw new ProductException("Product with ID %s not found".formatted(id));
        }

        this.productRepository.deleteById(id);
    }

    @Transactional
    public void purchaseProduct(List<ProductQuantityRequest> request) {
        for (ProductQuantityRequest item : request) {
            Product product = this.productRepository.findById(item.productId())
                    .orElseThrow(() -> new ProductException("Product with ID %s not found".formatted(item.productId())));

            if (item.quantity() < 0) {
                throw new ProductException("Restock quantity cannot be negative for product ID %s".formatted(item.productId()));
            }

            if (product.getStock() < item.quantity()) {
                throw new ProductException("Insufficient stock for product ID %s".formatted(item.productId()));
            }

            product.setStock(product.getStock() - item.quantity());
            this.productRepository.save(product);
        }
    }

    @Transactional
    public void restockProduct(List<ProductQuantityRequest> request) {
        for (ProductQuantityRequest item : request) {
            Product product = this.productRepository.findById(item.productId())
                    .orElseThrow(() -> new ProductException("Product with ID %s not found".formatted(item.productId())));

            if (item.quantity() < 0) {
                throw new ProductException("Restock quantity cannot be negative for product ID %s".formatted(item.productId()));
            }

            product.setStock(product.getStock() + item.quantity());
            this.productRepository.save(product);
        }
    }
}
