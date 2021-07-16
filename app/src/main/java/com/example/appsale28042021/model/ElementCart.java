package com.example.appsale28042021.model;

public class ElementCart {
    private int quantity;
    private Product product;

    // Cách 2:Sử dụng nguyên lý inversion of control
    public ElementCart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
