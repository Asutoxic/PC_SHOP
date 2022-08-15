package com.example.pc_shop.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Product {
    private int idProduct;
    private String title;
    private double price;
    private int quantity;
    private String image;
    private int idCategory;
    private String description;

    public Product() {
    }

    public Product(String title, double price, int quantity, String image, int idCategory, String description) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.idCategory = idCategory;
        this.description = description;
    }

    public Product(int idProduct, String title, double price, int quantity, String image, int idCategory, String description) {
        this.idProduct = idProduct;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.idCategory = idCategory;
        this.description = description;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @NotEmpty()
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Max(value = 10000, message = " < 10000 $ ")
    @Min(value = 1, message = "  > 1 and price < 10000 $ ")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Max(value = 10000, message = " < 10000 ")
    @Min(value = 1, message = "  > 1 ")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", idCategory=" + idCategory +
                ", description='" + description + '\'' +
                '}';
    }
}
