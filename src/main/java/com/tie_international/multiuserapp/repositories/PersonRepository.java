package com.tie_international.multiuserapp.repositories;

import com.tie_international.multiuserapp.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person getPersonByLastName(String lastName);
    Person getPersonById(Long id);

}