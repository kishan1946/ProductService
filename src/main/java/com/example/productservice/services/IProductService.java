package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getSingleProduct(long productId);
    Product addNewProduct(Product product);
    Product updateProduct(long productId, Product product);
    String deleteProduct(long productId);

}
