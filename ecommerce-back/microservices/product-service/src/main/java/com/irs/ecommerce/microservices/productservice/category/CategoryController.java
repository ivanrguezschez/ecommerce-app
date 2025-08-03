package com.irs.ecommerce.microservices.productservice.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }

    @PostMapping()
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.createCategory(request));
    }

    @PutMapping()
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryRequest request) {
        this.categoryService.updateCategory(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) throws Exception {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.accepted().build();
    }
}
