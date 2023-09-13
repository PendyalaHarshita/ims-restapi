package com.wellsfargo.training.ims.repository;

import com.wellsfargo.training.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/*
    * This interface has save(),findAll(),findById(),deleteById(),count()
      etc.. inbuilt methods of jpa repository for various database operations.
      This interface will be implemented by class automatically
   */
public interface ProductRepository extends JpaRepository<Product,Long> {
}
