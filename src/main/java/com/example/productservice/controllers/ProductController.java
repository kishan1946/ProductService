package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;

    ProductController(IProductService productService){
        this.productService = productService;
    }

//    @GetMapping()
//    public List<Product> getAllProduct(){
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable("id") long productId){
//        return null;
//    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) throws ParseException {
        Product product = this.getProduct(productDto);
        Product saveProduct = this.productService.addNewProduct(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        return responseEntity;
    }

//    @PatchMapping("/{id}")
//    public Product patchProduct(@PathVariable("id") long productId, @RequestBody Product product){
//        return null;
//    }
//
//    @PutMapping("{id}")
//    public Product updateProductById(@PathVariable("id") long productId, @RequestBody Product product){
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteProductById(@PathVariable("id") long productId){
//        return null;
//    }

    private Product getProduct(ProductDto productDto) throws ParseException {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setPublic(true);
        product.setDeleted(false);
        product.setCreatedDate(this.currentDate());
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
