package com.example.appsale28042021.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance = null;
    private List<ElementCart> listCarts;
    private int total;

    private Cart() {
        listCarts = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public List<ElementCart> getCarts() {
        return listCarts;
    }

    public void updateCart(Product product) {
        ElementCart elementCart = new ElementCart(1, product);

        if (listCarts != null) {
            if (listCarts.size() > 0) {
                for (int i = 0; i < listCarts.size(); i++) {
                    if (listCarts.get(i).getProduct().getId() == elementCart.getProduct().getId()) {
                        listCarts.get(i).setQuantity(listCarts.get(i).getQuantity() + 1);
                        return;
                    }
                }
            }
            listCarts.add(elementCart);
        }
    }

    public int totalCart() {
        total = 0;
        for (ElementCart elementCart : Cart.getInstance().getCarts()) {
            int quantity = elementCart.getQuantity();
            float saleOff = elementCart.getProduct().getSaleOf();
            long price = elementCart.getProduct().getPrice();

            total += (int) ((price * ((100 - saleOff) / 100)) * quantity);
        }
        return total;
    }
}
