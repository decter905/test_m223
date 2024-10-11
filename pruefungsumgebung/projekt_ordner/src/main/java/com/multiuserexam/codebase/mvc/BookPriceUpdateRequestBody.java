package com.multiuserexam.codebase.mvc;

/**
 * Diese Klasse stellt den Body für Requests dar, mit denen der Preis eines Buches angepasst werden soll.
 */
public class BookPriceUpdateRequestBody {

    private String isbn;
    private Double newPrice;

    public String getIsbn() {
        return isbn;
    }

    public Double getNewPrice() {
        return newPrice;
    }
}
