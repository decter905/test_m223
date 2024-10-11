package com.multiuserexam.codebase.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.multiuserexam.codebase.entities.Book;
import com.multiuserexam.codebase.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();

        List<Book> books = Arrays.asList(
                new Book("3551551677", "Harry Potter", 20.99, "J.K. Rowling"),
                new Book("1234567890", "The Hobbit", 15.99, "J.R.R. Tolkien")
        );

        bookRepository.saveAll(books);
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/getAllBooks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$[0].isbn").value("3551551677")) // Validate first book's ISBN
                .andExpect(jsonPath("$[0].name").value("Harry Potter")) // Validate first book's name
                .andExpect(jsonPath("$[1].isbn").value("1234567890")) // Validate second book's ISBN
                .andExpect(jsonPath("$[1].name").value("The Hobbit")); // Validate second book's name
    }
}
