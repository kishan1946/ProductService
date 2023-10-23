package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    List<Category> getAllProduct();
    Category getSingleProduct(long categoryId);
    Category getSingleProduct(String categoryName);
    Category addNewProduct(Category category);
    Category updateProduct(long categoryId, Category category);
    String deleteProduct(long categoryId);

}
