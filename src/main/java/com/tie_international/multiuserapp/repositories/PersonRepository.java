package com.tie_international.multiuserapp.repositories;

import com.tie_international.multiuserapp.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person getPersonByLastName(String lastName);
    Person getPersonById(Long id);
}