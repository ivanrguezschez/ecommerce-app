package com.irs.ecommerce.microservices.productservice.category;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obtiene todas las categorias", description = "Recupera todas las categorias.")
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @Operation(summary = "Obtiene una categoria específica", description = "Recupera una categoría específica.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }

    @Operation(summary = "Crea una nueva categoria", description = "Inserta una nueva categoría.")
    @PostMapping()
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(this.categoryService.createCategory(request));
    }

    @Operation(summary = "Modifica una categoria específica", description = "Actualiza una categoría específica.")
    @PutMapping()
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryRequest request) {
        this.categoryService.updateCategory(request);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Elimina una categoria específica", description = "Borra una categoria específica.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) throws Exception {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.accepted().build();
    }
}
