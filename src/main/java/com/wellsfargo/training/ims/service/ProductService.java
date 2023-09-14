package com.wellsfargo.training.ims.service;

import com.wellsfargo.training.ims.model.Product;
import com.wellsfargo.training.ims.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
 * A service layer is an additional layer in an MVC application that
 * mediates communication between a controller and repository layer.
 * The service layer contains business logic.
 * In particular, it contains validation logic. */

/* @Service annotation allows developers to add business functionalities.
 *  @Transactional annotation allows to manage Database transactions efficiently */
@Service
@Transactional
public class ProductService {
    @Autowired
    /*@Autowired - marks a constructor, field, or setter method to be autowired by Spring dependency injection. */
    private ProductRepository productRepository;

    public Product saveProduct(Product p){
        return productRepository.save(p);
    }
    public List<Product> findall(){
        return productRepository.findAll();
    }
    public Optional<Product> getSingleProduct(long id){
        return productRepository.findById(id);
    }
    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
