package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ICategoryService;
import com.example.productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;
    ICategoryService categoryService;

    ProductController(IProductService productService, ICategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList = this.productService.getAllProduct();
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productList,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){
        Product product = this.productService.getSingleProduct(productId);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = this.getProduct(productDto);
        Product saveProduct = this.productService.addNewProduct(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable("id") long productId, @RequestBody Map<String, Object> productMap){
        Product product = this.productService.patchProduct(productId,productMap);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") long productId, @RequestBody ProductDto productDto) {
        Product product = this.updateProduct(productDto);
        Product saveProduct = this.productService.updateProduct(productId,product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(saveProduct,HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<List<Product>> deleteProductById(@PathVariable("id") long productId){
        List<Product> productList = this.productService.deleteProduct(productId);
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productList,HttpStatus.OK);
        return responseEntity;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = this.categoryService.getSingleProduct(productDto.getCategory());
        if(category == null){
            category = new Category();
            category.setName(productDto.getCategory());
            category.setDescription(productDto.getCategory());
            category.setCreatedDate(this.currentDate());
            category.setUpdatedDate(this.currentDate());
        }
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPublic(true);
        product.setDeleted(false);
        product.setCreatedDate(this.currentDate());
        product.setUpdatedDate(this.currentDate());
        return product;
    }
    private Product updateProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = this.categoryService.getSingleProduct(productDto.getCategory());
        if(category == null){
            category = new Category();
            category.setName(productDto.getCategory());
            category.setDescription(productDto.getCategory());
            category.setCreatedDate(this.currentDate());
            category.setUpdatedDate(this.currentDate());
        }
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPublic(true);
        product.setDeleted(false);
        product.setUpdatedDate(this.currentDate());
        return product;
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
