package com.multiuserexam.codebase.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.multiuserexam.codebase.entities.Book;
import com.multiuserexam.codebase.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.multiuserexam.codebase.mvc.ApplicationService;
import com.multiuserexam.codebase.repository.BookRepository;


@SpringBootTest
public class OrderTest {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testOrderBooks() {
        Book book = new Book("3551551677", "Harry Potter", 20.99, "Donald Trump");
        book.setInventoryCount(5);
        bookRepository.save(book);

        boolean result = applicationService.orderBooks("3551551677", 10);
        assertTrue(result, "The order should be successful and inventory should be updated");
    }
}
