package com.example.appsale28042021.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsale28042021.R;
import com.example.appsale28042021.adapter.ProductAdapter;
import com.example.appsale28042021.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRcvProduct;
    List<Product> mListProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcvProduct = findViewById(R.id.recyclerviewProduct);
        mToolbar = findViewById(R.id.toolbarProduct);
        setSupportActionBar(mToolbar);

        mListProduct = Product.getMockListProduct();


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        mProductAdapter = new ProductAdapter(mListProduct, this, width , height);

        mRcvProduct.setAdapter(mProductAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}