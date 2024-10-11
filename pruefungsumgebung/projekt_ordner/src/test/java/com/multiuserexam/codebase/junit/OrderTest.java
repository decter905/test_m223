package com.multiuserexam.codebase.junit;

import com.multiuserexam.codebase.entities.Book;
import com.multiuserexam.codebase.mvc.ApplicationService;
import com.multiuserexam.codebase.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class OrderTest {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testOrderBooks() {
        Book book = new Book("3551551677", "Harry Potter und der Stein der Weisen", 10, "Donald Trump");
        book.setInventoryCount(5);
        bookRepository.save(book);

        boolean result = applicationService.orderBooks("3551551677", 10);
        assertTrue(result, "The order should be successful and inventory should be updated");
    }
}
