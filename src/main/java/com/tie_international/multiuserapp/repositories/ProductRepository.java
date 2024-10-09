package com.tie_international.multiuserapp.repositories;

import com.tie_international.multiuserapp.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<List<Product>> findProductByPrice(Double price);

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<List<Product>> findProductById(Long id);

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<List<Product>> findProductByName(String name);
}