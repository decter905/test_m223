package com.multiuserexam.codebase.mvc;

import com.multiuserexam.codebase.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return applicationService.getAllBooks();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooksByName(@RequestParam("name") String bookName) {
        return applicationService.getBooksByName(bookName);
    }

    @PutMapping("/admin/changePrice")
    public ResponseEntity<String> changeBookPrice(@RequestBody BookPriceUpdateRequestBody requestBody) {
        if (requestBody.getNewPrice() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price cannot be negative");
        }

        boolean isUpdated = applicationService.updateBookPrice(requestBody.getIsbn(), requestBody.getNewPrice());
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        return ResponseEntity.ok("Price updated successfully");
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "Hello admin.";
    }
}


