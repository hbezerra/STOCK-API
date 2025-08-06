package com.stock.controller;

import com.stock.exception.ProductException;
import com.stock.model.Product;
import com.stock.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    // FUNCTIONS ;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        try {
            Product newProduct = service.saveProduct(product);
            return ResponseEntity.ok(newProduct);
        }
        catch (ProductException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
