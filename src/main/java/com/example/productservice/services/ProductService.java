package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;

    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product getSingleProduct(long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        return null;
    }

    @Override
    public String deleteProduct(long productId) {
        return null;
    }
}
