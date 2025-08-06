package com.stock.service;

import com.stock.model.Product;
import com.stock.repository.ProductRepository;
import com.stock.util.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductValidator validator ;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator) {
        this.repository = productRepository;
        this.validator = productValidator;
    }

    // FUNCTIONS ;

    public Product saveProduct(Product product) {
        validator.validateProduct(product);
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

}
