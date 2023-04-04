package com.example.pagingandsorting;

import com.example.pagingandsorting.entety.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Page<Product> findByPrice(double price, Pageable pageable);
}
