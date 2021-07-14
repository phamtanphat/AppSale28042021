package com.example.appsale28042021.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    SearchView mSearchView;
    CountDownTimer mCountDownTimer;
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProductAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mCountDownTimer != null){
                    mCountDownTimer.cancel();
                    mCountDownTimer = null;
                }
                mCountDownTimer = new CountDownTimer(2500 , 2000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        mProductAdapter.getFilter().filter(newText);
                    }
                };
                mCountDownTimer.start();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}