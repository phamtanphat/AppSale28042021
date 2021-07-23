package com.example.appsale28042021.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void setListCart(List<ElementCart> listCart){
        if (listCart == null){
            listCart = new ArrayList<>();
        }
        this.listCarts = listCart;
    }

    public int totalCart() {
        total = 0;
        for (ElementCart elementCart : Cart.getInstance().getCarts()) {
            int quantity = elementCart.getQuantity();
            float saleOff = elementCart.getProduct().getSaleOf();
            long price = elementCart.getProduct().getPrice();

            total = total + (int) ((price * ((100 - saleOff) / 100)) * quantity);
        }
        return total;
    }

    public JSONArray createJson(List<ElementCart> elementCarts) {
        JSONArray jsonArray = new JSONArray();
        if (elementCarts.size() == 0) {
            return jsonArray;
        }
        for (int i = 0; i < elementCarts.size(); i++) {
            try {

                JSONObject jsonObjectProduct = new JSONObject();
                jsonObjectProduct.put("id", elementCarts.get(i).getProduct().getId());
                jsonObjectProduct.put("name", elementCarts.get(i).getProduct().getName());
                jsonObjectProduct.put("price", elementCarts.get(i).getProduct().getPrice());
                jsonObjectProduct.put("saleOf", elementCarts.get(i).getProduct().getSaleOf());
                jsonObjectProduct.put("image", elementCarts.get(i).getProduct().getImage());


                JSONObject jsonObjectElementCart = new JSONObject();
                jsonObjectElementCart.put("quantity", elementCarts.get(i).getQuantity());
                jsonObjectElementCart.put("product", jsonObjectProduct);

                jsonArray.put(jsonObjectElementCart);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public List<ElementCart> tranFormStringToListElementCart(String data) {
        List<ElementCart> elementCarts = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectElementCart = (JSONObject) jsonArray.get(i);
                long quantity = jsonObjectElementCart.getInt("quantity");

                JSONObject jsonObjectProduct = jsonObjectElementCart.getJSONObject("product");

                int id = jsonObjectProduct.getInt("id");
                String name = jsonObjectProduct.getString("name");
                long price = jsonObjectProduct.getLong("price");
                double saleOf = jsonObjectProduct.getDouble("saleOf");
                int image = jsonObjectProduct.getInt("image");

                elementCarts.add(new ElementCart((int) quantity, new Product(id, image, name, price, (float) saleOf)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elementCarts;
    }
}
