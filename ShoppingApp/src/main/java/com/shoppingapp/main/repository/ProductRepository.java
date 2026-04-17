package com.shoppingapp.main.repository;

import com.shoppingapp.main.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
