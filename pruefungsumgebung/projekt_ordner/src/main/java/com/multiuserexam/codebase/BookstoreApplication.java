package com.multiuserexam.codebase;

// Import der Supplier API
import com.multiuserexam.codebase.api.SupplierApi;
import org.springframework.boot.SpringApplication;

public class BookstoreApplication {

    public static final SupplierApi supplierApi = new SupplierApi();

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
