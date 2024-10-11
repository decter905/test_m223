package com.multiuserexam.codebase.entities;

import jakarta.persistence.Column;

public class Book {

    // TODO: Implementiere weitere Attribute und Methoden wenn nötig.

    @Column(unique = true)
    private String isbn;

    private String name;
    private double price;

    Book(String isbn, String name, double price) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String newIsbn) {
        isbn = newIsbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }
}
