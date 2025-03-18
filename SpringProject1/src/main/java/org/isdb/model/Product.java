package org.isdb.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Product {

    private long id;
    private String name;
	private double amount;
    private String brand;
@JsonFormat(pattern="yyyy-mm-dd")
  private LocalDate purchaseDate;


public Product() {
}
public Product(long id, String name, double amount, String brand, LocalDate purchaseDate) {
	this.id = id;
	this.name = name;
	this.amount = amount;
	this.brand = brand;
	this.purchaseDate = purchaseDate;
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
public void setName(String name) {
	this.name = name;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}

public void setPurchaseDate(LocalDate purchaseDate) {
	this.purchaseDate = purchaseDate;
}
public LocalDate getPurchaseDate() {
	return purchaseDate;
}


}
