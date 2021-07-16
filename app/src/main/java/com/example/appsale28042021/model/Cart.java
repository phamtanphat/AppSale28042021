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

    public void updateCart(Product product){
        ElementCart elementCart = new ElementCart(1, product);

        if (listCarts != null){
            if (listCarts.size() > 0){
                int count = 0;
                for (int i = 0; i <listCarts.size() ; i++) {
                    if (listCarts.get(i).getProduct().getId() == elementCart.getProduct().getId()){
                        listCarts.get(i).setQuantity(listCarts.get(i).getQuantity() + 1);
                        count++;
                        break;
                    }
                }
                if(count == 0){
                    listCarts.add(elementCart);
                }
            }else{
                listCarts.add(elementCart);
            }
        }

    }
}
