package com.codewithdave.store.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // String
  List<Product> findByName(String name);
  List<Product> findByNameLike(String name);
  List<Product> findByNameNotLike(String name);
  List<Product> findByNameContaining(String name);
  List<Product> findByNameStartingWith(String name);
  List<Product> findByNameEndingWith(String name);
  List<Product> findByNameEndingWithIgnoreCase(String name);

  // Numbers
  List<Product> findByPrice(BigDecimal price);
  List<Product> findByPriceGreaterThan(BigDecimal price);
  List<Product> findByPriceGreaterThanEqual(BigDecimal price);
  List<Product> findByPriceLessThanEqual(BigDecimal price);
  List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

  // Null
  List<Product> findByDescriptionNull();
  List<Product> findByDescriptionNotNull();

  // Multiple conditions
  List<Product> findByDescriptionNullAndNameNull();

  // Sort (OrderBy)
  List<Product> findByNameOrderByPrice(String name);

  // Limit (Top/First)
  List<Product> findTop5ByNameOrderByPrice(String name);
  List<Product> findFirst5ByNameLikeOrderByPrice(String name);
}
