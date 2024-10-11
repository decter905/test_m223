package com.multiuserexam.codebase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;
import com.multiuserexam.codebase.entities.Book;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    // Ich habe mich für REPEATABLE_READ entschieden, da es sich hierbei um eine interne Platform mit Mitarbeitern handelt, die die Bücher verwalten. Um dennoch Dirty Reads zu verhinden, aber jedoch nicht den Workflow zu verlangsamen, habe ich mich für dieses Isolation-Level entschieden.
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = {SQLException.class})
    Optional<Book> findByIsbn(String isbn);

}
