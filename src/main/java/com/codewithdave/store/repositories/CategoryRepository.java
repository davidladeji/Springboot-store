package com.codewithdave.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
    
}
