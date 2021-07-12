package com.example.appsale28042021.model;

import com.example.appsale28042021.R;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Product> getMockListProduct(){
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product(1 , R.drawable.hinh_galaxy_a52_8gb,"Điện thoại Samsung Galaxy A52",10290000,8.0f));
        listProduct.add(new Product(2 , R.drawable.hinh_iphone_11_64gb,"Điện thoại iPhone 11 64GB",17490000,13.0f));
        listProduct.add(new Product(3 , R.drawable.hinh_iphone_12_pro_max_128gb,"Điện thoại iPhone 12 Pro Max 128GB",32990000,7.0f));
        listProduct.add(new Product(4 , R.drawable.hinh_oppo_reno5,"Điện thoại OPPO Reno5 5G",11990000,11.0f));
        listProduct.add(new Product(5 , R.drawable.hinh_realme_c2,"Điện thoại Realme C21Y 4GB",3990000,7.0f));
        listProduct.add(new Product(6 , R.drawable.hinh_samsung_galaxy_a32,"Điện thoại Samsung Galaxy A32",6690000,10.0f));
        listProduct.add(new Product(7 , R.drawable.hinh_vivo_y20s,"Điện thoại Vivo Y20s",4990000,8.0f));
        listProduct.add(new Product(8 , R.drawable.hinh_vivo_y51,"Điện thoại Vivo Y51",6290000,11.0f));
        listProduct.add(new Product(9 , R.drawable.hinh_xiaomi_11_lite,"Điện thoại Xiaomi Mi 11 Lite",7990000,11.0f));
        listProduct.add(new Product(10 , R.drawable.hinh_xiaomi_redmi_9t,"Điện thoại Xiaomi Redmi 9T",4990000,16.0f));

        return listProduct;
    }
}
