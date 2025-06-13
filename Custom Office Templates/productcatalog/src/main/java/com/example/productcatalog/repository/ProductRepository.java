//package com.example.productcatalog.repository;
//
//public interface ProductRepository {
//}
package com.example.productcatalog.repository;

import com.example.productcatalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Optional annotation
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom method to find products by category
    List<Product> findByCategory(String category);
}
