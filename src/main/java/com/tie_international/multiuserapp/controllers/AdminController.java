package com.tie_international.multiuserapp.controllers;

import com.tie_international.multiuserapp.entities.Person;
import com.tie_international.multiuserapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class AdminController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("admin/getPersonById/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {
        return personRepository.getPersonById(id);
    }

    @GetMapping("admin/getPersonByName/{name}")
    public Person getPersonByLastName(@PathVariable("name") String name) {
        return personRepository.getPersonByLastName(name);
    }
}