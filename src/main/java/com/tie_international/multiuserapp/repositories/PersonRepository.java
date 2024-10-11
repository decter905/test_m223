package com.tie_international.multiuserapp.repositories;

import com.tie_international.multiuserapp.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<List<Person>> findAllByLastName(@NonNull String lastName);
}
