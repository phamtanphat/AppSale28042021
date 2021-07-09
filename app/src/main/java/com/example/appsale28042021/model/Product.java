package com.example.appsale28042021.model;

public class Product {
    private int id;
    private int image;
    private String name;
    private long price;
    private float saleOf;

    public Product(int id, int image, String name, long price, float saleOf) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.saleOf = saleOf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public float getSaleOf() {
        return saleOf;
    }

    public void setSaleOf(float saleOf) {
        this.saleOf = saleOf;
    }
}
