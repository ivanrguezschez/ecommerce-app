package com.irs.ecommerce.microservices.productservice.category;

import com.irs.ecommerce.microservices.productservice.exceptions.CategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategories() {
        return this.categoryRepository.findAll().stream()
                .map(this.categoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse getCategoryById(Integer id) {
        return this.categoryRepository
                .findById(id)
                .map(this.categoryMapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryException("Category with id %s not found".formatted(id)));
    }

    public Integer createCategory(CategoryRequest request) {
        Category category = this.categoryMapper.toCategory(request);
        return this.categoryRepository.save(category).getId();
    }

    public Integer updateCategory(CategoryRequest request) {
        Category category = this.categoryMapper.toCategory(request);
        if (request.id() == null) {
            throw new CategoryException("Category ID cannot be null");
        }
        else if (!this.categoryRepository.existsById(request.id())) {
            throw new CategoryException("Category with ID %s not found".formatted(request.id()));
        }
        this.categoryRepository.save(category);
        return category.getId();
    }

    public void deleteCategory(Integer id) {
        if (id == null ) {
            throw new CategoryException("Category ID cannot be null or blank");
        }
        else if (!this.categoryRepository.existsById(id)) {
            throw new CategoryException("Category with id %s not found".formatted(id));
        }
        this.categoryRepository.deleteById(id);
    }
}
