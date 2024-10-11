package com.tie_international.multiuserapp.testing;

import com.tie_international.multiuserapp.entities.Person;
import com.tie_international.multiuserapp.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @Test
    public void testFindByLastNameWithValidName() {
        // Arrange
        Person person = new Person("John", "Doe");
        personRepository.save(person);

        // Act
        Optional<List<Person>> foundPersonsOptional = personRepository.findAllByLastName("Doe");

        // Assert
        assertTrue(foundPersonsOptional.isPresent(), "Expected a non-empty result");
        List<Person> foundPersons = foundPersonsOptional.get();
        assertEquals(1, foundPersons.size());
        assertEquals("John", foundPersons.get(0).getFirstName());
    }

    @Test
    public void testFindByLastNameWithNonExistentName() {
        // Act
        Optional<List<Person>> foundPersonsOptional = personRepository.findAllByLastName("NonExistent");

        // Assert
        assertTrue(foundPersonsOptional.isPresent(), "Expected an empty list");
        assertTrue(foundPersonsOptional.get().isEmpty(), "Expected no results for a non-existent last name");
    }

    @Test
    public void testFindByIdWithValidId() {
        // Arrange
        Person person = new Person("Jane", "Smith");
        personRepository.save(person);
        Long id = person.getId();

        // Act
        Optional<Person> foundPersonOptional = personRepository.findById(id);

        // Assert
        assertTrue(foundPersonOptional.isPresent(), "Expected a non-empty result");
        Person foundPerson = foundPersonOptional.get();
        assertEquals("Jane", foundPerson.getFirstName());
        assertEquals("Smith", foundPerson.getLastName());
    }

    @Test
    public void testFindByIdWithNonExistentId() {
        // Act
        Optional<Person> foundPersonOptional = personRepository.findById(-1L);

        // Assert
        assertTrue(foundPersonOptional.isEmpty(), "Expected no results for a non-existent ID");
    }
}
