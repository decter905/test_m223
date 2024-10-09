package com.tie_international.multiuserapp.services;

import com.tie_international.multiuserapp.entities.Product;
import com.tie_international.multiuserapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void updateAllPrices(double factor) {
        Iterable<Product> allProducts = productRepo.findAll();
        for (Product currentProduct : allProducts) {
            currentProduct.setPrice(currentProduct.getPrice() * factor);
            productRepo.save(currentProduct);
        }
    }

    public void addProducts(int amount) {
        for (int i = 1; i <= amount; i++) {
            Product product = new Product("Product " + i, 10 * i);
            productRepo.save(product);
        }
    }

    public Optional<List<Product>> getProductByPrice(double price) {
        return productRepo.findProductByPrice(price);
    }

    public Optional<List<Product>> getProductById(long id) {
        return productRepo.findProductById(id);
    }

    public Optional<List<Product>> getProductByName(String name) {
        return productRepo.findProductByName(name);
    }

}