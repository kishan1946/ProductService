package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> getAllProduct();
    Product getSingleProduct(long productId);
    Product addNewProduct(Product product);
    Product updateProduct(long productId, Product product);
    Product patchProduct(long productId, Map<String,Object> productMap);
    List<Product> deleteProduct(long productId);

}
