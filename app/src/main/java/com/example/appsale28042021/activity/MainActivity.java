package com.example.appsale28042021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.appsale28042021.R;
import com.example.appsale28042021.adapter.ProductAdapter;
import com.example.appsale28042021.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRcvProduct;
    List<Product> mListProduct;
    ProductAdapter mProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcvProduct = findViewById(R.id.recyclerviewProduct);
        mListProduct = Product.getMockListProduct();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        mProductAdapter = new ProductAdapter(mListProduct, this, width , height);



        mRcvProduct.setAdapter(mProductAdapter);

    }
}