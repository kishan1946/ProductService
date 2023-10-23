package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {
    ProductRepository productRepository;

    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = this.productRepository.findAllByIsPublicTrue();
        return productList;
    }

    @Override
    public Product getSingleProduct(long productId) {
        Product product = this.productRepository.findProductById(productId);
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        Product getProduct = this.productRepository.findProductById(productId);
        product.setId(productId);
        product.setCreatedDate(getProduct.getCreatedDate());
        return this.productRepository.save(product);
    }

    @Override
    public Product patchProduct(long productId, Map<String, Object> productMap) {
        Product existingProduct = this.productRepository.findProductById(productId);
        if(existingProduct.isPublic()) {
            productMap.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class,key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingProduct,value);
            });
            existingProduct.setUpdatedDate(this.currentDate());
            return this.productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public List<Product> deleteProduct(long productId) {
        Product product = this.productRepository.findProductById(productId);
        product.setPublic(false);
        product.setDeleted(true);
        product.setUpdatedDate(this.currentDate());
        this.productRepository.save(product);
        List<Product> productList = this.productRepository.findAllByIsPublicTrue();
        return productList;
    }

    private Date currentDate(){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Step 2: Format the date as a string in "yyyyMMdd_HHmmss" format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedDate = dateFormat.format(currentDate);

        System.out.println("Formatted Date String: " + formattedDate);

        try {
            // Step 3: Parse the formatted string back into a Date object
            Date parsedDate = dateFormat.parse(formattedDate);
            return parsedDate;
//            System.out.println("Parsed Date: " + parsedDate);
        } catch (ParseException e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
        return null;
    }
}
