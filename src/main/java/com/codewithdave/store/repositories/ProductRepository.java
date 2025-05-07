package com.codewithdave.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
