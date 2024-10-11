package com.multiuserexam.codebase.repository;

import com.multiuserexam.codebase.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    // Ich habe mich für REPEATABLE_READ entschieden,
    // da es sich hierbei um eine interne Platform mit Mitarbeitern handelt, die die Bücher verwalten.
    // Um dennoch Dirty Reads zu verhindern, aber jedoch nicht den Workflow zu verlangsamen,
    // habe ich mich für dieses Isolation-Level entschieden.
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<Book> findByIsbn(String isbn);

}
