package com.codewithdave.store.repositories;

import java.util.List;

import com.codewithdave.store.entities.Product;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, double minPrice, double maxPrice);
}
