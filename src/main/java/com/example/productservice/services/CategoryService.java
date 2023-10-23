package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllProduct() {
        return null;
    }

    @Override
    public Category getSingleProduct(long categoryId) {
        return null;
    }

    @Override
    public Category getSingleProduct(String categoryName) {
        Category category = this.categoryRepository.findCategoryByName(categoryName);
        return category;
    }

    @Override
    public Category addNewProduct(Category category) {
        return null;
    }

    @Override
    public Category updateProduct(long categoryId, Category category) {
        return null;
    }

    @Override
    public String deleteProduct(long categoryId) {
        return null;
    }
}
