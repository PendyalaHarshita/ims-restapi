package com.wellsfargo.training.ims.repository;

import com.wellsfargo.training.ims.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author rajgs
 * JPA Repository is mainly used for managing the data in a Spring Boot Application.
 * JpaRepository is particularly a JPA specific extension for Repository.
 * Jpa Repository contains the APIs for basic CRUD operations, the APIS for
 * pagination, and the APIs for sorting.
 * This Layer interacts with Database
 */
public interface DealerRepository extends JpaRepository<Dealer,Long> {
    //Custom method to fetch object/record based on email field - non id field
    Optional<Dealer> findByEmail(String email);
}
