package com.tie_international.multiuserapp.repositories;

import com.tie_international.multiuserapp.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductByPrice(Double price);
    Product findProductById(Long id);
    Product findProductByName(String name);
}