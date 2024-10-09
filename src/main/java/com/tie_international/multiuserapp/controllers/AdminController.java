package com.tie_international.multiuserapp.controllers;

import com.tie_international.multiuserapp.entities.Person;
import com.tie_international.multiuserapp.entities.Product;
import com.tie_international.multiuserapp.services.PersonService;
import com.tie_international.multiuserapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/")
public class AdminController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ProductService productService;

    @GetMapping("getAllPeople")
    public Optional<Iterable<Person>> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping("getPersonById/{id}")
    public Optional<List<Person>>getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping("getPersonByName/{name}")
    public Optional<List<Person>> getPersonByLastName(@PathVariable("name") String name) {
        return personService.getPersonByLastName(name);
    }

    @PostMapping("addPerson")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping("deletePerson/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @PutMapping("updateAll/{factor}")
    public void updateAllPrices(@PathVariable("factor") Double factor) {
        productService.updateAllPrices(factor);
    }

    @PutMapping("product/updateAllPrices")
    public void addProducts(Integer amount) {
        productService.addProducts(amount);
    }

    @GetMapping("product/getProductByPrice/{price}")
    public Optional<List<Product>> getProductByPrice(@PathVariable("price") Double price) {
        return productService.getProductByPrice(price);
    }

    @GetMapping("product/getProductById/{id}")
    public Optional<List<Product>> getProductByPrice(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("product/getProductByName/{name}")
    public Optional<List<Product>> getProductByName(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }
}
