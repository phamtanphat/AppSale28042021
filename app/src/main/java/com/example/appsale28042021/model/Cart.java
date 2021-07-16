package com.example.appsale28042021.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance = null;
    private List<ElementCart> listCarts;

    private Cart() {
        listCarts = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null){
            instance = new Cart();
        }
        return instance;
    }

    public List<ElementCart> getCarts(){
        return listCarts;
    }

}
