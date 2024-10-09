package com.tie_international.multiuserapp.services;

import com.tie_international.multiuserapp.entities.Person;
import com.tie_international.multiuserapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepo;

    @Autowired
    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Optional<Iterable<Person>> getAllPeople() {
        return Optional.of(personRepo.findAll());
    }

    public Optional<List<Person>> getPersonById(Long id) {
        return personRepo.findAllById(id);
    }

    public Optional<List<Person>> getPersonByLastName(String name) {
        return personRepo.findAllByLastName(name);
    }

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }
}
