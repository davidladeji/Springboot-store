package com.codewithdave.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.codewithdave.store.dtos.ProductSummaryDTO;
import com.codewithdave.store.entities.Category;
import com.codewithdave.store.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository {
    // String
  List<Product> findByName(String name);
  List<Product> findByNameLike(String name);
  List<Product> findByNameNotLike(String name);
  List<Product> findByNameContaining(String name);
  List<Product> findByNameStartingWith(String name);
  List<Product> findByNameEndingWith(String name);
  List<Product> findByNameEndingWithIgnoreCase(String name);

  // Numbers
  List<Product> findByPrice(double price);
  List<Product> findByPriceGreaterThan(double price);
  List<Product> findByPriceGreaterThanEqual(double price);
  List<Product> findByPriceLessThanEqual(double price);
  List<Product> findByPriceBetween(double min, double max);

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

  // Find products whose prices are in a given range and sort by name
  @Procedure("findProductByPrice")
  List<Product> findProducts(double min, double max);

  @Query("select count(*) from Product p where p.price between :min and :max")
  long countProducts(@Param("min") double min, @Param("max") double max);

  @Modifying
  @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
  void updatePriceByCategory(double newPrice, Byte categoryId);

//   Projections (class version)
  @Query("select new com.codewithdave.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
  List<ProductSummaryDTO> findByCategory(@Param("category") Category category);

//   Projections (interface version)
//   @Query("select p.id, p.name from Product p where p.category = :category")
//   List<ProductSummary> findByCategory(@Param("category") Category category);
}
