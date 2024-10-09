package com.tie_international.multiuserapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** Class representing a product available in the shop.
*/ 
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private double price;

	protected Product() {}

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
	}

	public void setPrice(double newPrice) {
		price = newPrice;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return "[" + name + ", " + Double.toString(price) + " CHF]";
	}

}
