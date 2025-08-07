package com.stock.util;

import com.stock.exception.ProductException;
import com.stock.model.Product;
import com.stock.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductRepository repository ;

    public ProductValidator(ProductRepository productRepository){
        this.repository = productRepository;
    }

    // FUNCTIONS ;

    public void validateProduct(Product product){
        if(repository.existsByName(product.getName())){
            throw new ProductException("Error adding product! A product with the given name already exists.");
        }
        if(product.getQuantity() < 0) {
            throw new ProductException("Error adding product! The quantity must be greater than 0");
        }
    }

    // public void productExistsById(Long id) {
        // if(!repository.existsById(id)) {
           // throw new ProductException("Error updating product. The ID entered is invalid");
        // }
    // }

}
