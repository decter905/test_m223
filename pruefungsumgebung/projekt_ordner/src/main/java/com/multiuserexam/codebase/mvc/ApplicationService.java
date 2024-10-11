package com.multiuserexam.codebase.mvc;

import com.multiuserexam.codebase.BookstoreApplication;
import com.multiuserexam.codebase.api.SupplierApi;
import com.multiuserexam.codebase.entities.Book;
import com.multiuserexam.codebase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private BookRepository bookRepository;


    private final SupplierApi supplierApi = new SupplierApi();

    ApplicationService() {}

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> getBooksByName(String bookName) {
        return ((List<Book>) bookRepository.findAll()).stream()
                .filter(book -> book.getName().toLowerCase().contains(bookName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean updateBookPrice(String isbn, double newPrice) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            Book bookToUpdate = book.get();
            bookToUpdate.setPrice(newPrice);
            bookRepository.save(bookToUpdate);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean orderBooks(String isbn, int quantity) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            try {
                Pair<String, Boolean> response = supplierApi.enterOrder(isbn, quantity);

                if (response.getSecond()) {
                    book.setInventoryCount(book.getInventoryCount() + quantity);
                    bookRepository.save(book);

                    logAPICall("Order successful for ISBN: " + isbn + ", Quantity: " + quantity);
                    return true;
                } else {
                    // Rollback transaction if order failed
                    logAPICall("Order failed, rolling back for ISBN: " + isbn + ", Quantity: " + quantity);
                    return false;
                }

            } catch (Exception e) {
                logAPICall("Exception occurred while processing order: " + e.getMessage());
                return false;
            }
        } else {
            logAPICall("Book not found for ISBN: " + isbn);
            return false;
        }
    }

    private void logAPICall(String message) {
        try (FileWriter writer = new FileWriter("API_calls.log", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
