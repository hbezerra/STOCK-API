package com.stock.service;

import com.stock.exception.ProductException;
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

    public Product updateProduct(Long id, Product product) {
        Product productUpdate = validator.findProductById(id);
        validator.validateProduct(product);
        productUpdate.setName(product.getName());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setQuantity(product.getQuantity());
        return repository.save(productUpdate);
    }

    public Product incrementQuantityProduct(Long id, Integer quantity) {
        validator.validateQuantity(quantity);
        Product productUpdate = validator.findProductById(id);
        productUpdate.setQuantity(productUpdate.getQuantity() + quantity);
        return repository.save(productUpdate);
    }

    public Product decrementQuantityProduct(Long id, Integer quantity) {
        validator.validateQuantity(quantity);
        Product productUpdate = validator.findProductById(id);
        validator.validateQuantityDecrement(productUpdate.getQuantity(), quantity);
        productUpdate.setQuantity(productUpdate.getQuantity() - quantity);
        return repository.save(productUpdate);
    }

}
