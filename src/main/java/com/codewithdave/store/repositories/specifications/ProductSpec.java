package com.codewithdave.store.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.codewithdave.store.entities.Product;

public class ProductSpec {
    public static Specification<Product> hasName(String name){
        
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(double price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(double price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }
}
