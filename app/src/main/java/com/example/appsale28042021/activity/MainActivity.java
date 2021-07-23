package com.example.appsale28042021.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsale28042021.R;
import com.example.appsale28042021.adapter.ProductAdapter;
import com.example.appsale28042021.interfaces.OnItemClickAdapter;
import com.example.appsale28042021.model.Cart;
import com.example.appsale28042021.model.ElementCart;
import com.example.appsale28042021.model.Product;
import com.example.appsale28042021.shared.AppCache;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRcvProduct;
    List<Product> mListProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    SearchView mSearchView;
    CountDownTimer mCountDownTimer;
    TextView mTvCount;
    RelativeLayout mRelativeBackgroundCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcvProduct = findViewById(R.id.recyclerviewProduct);
        mToolbar = findViewById(R.id.toolbarProduct);

        if (AppCache.readFile(this) != null){
            List<ElementCart> elementCartList = Cart.getInstance().tranFormStringToListElementCart(AppCache.readFile(this));
            Cart.getInstance().setListCart(elementCartList);
        }


        setSupportActionBar(mToolbar);

        mListProduct = Product.getMockListProduct();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        mProductAdapter = new ProductAdapter(mListProduct, this, width , height);

        mRcvProduct.setHasFixedSize(true);
        mRcvProduct.setAdapter(mProductAdapter);




        mProductAdapter.setOnItemClickAdapter(new OnItemClickAdapter() {
            @Override
            public void onClick(int position) {
                Cart.getInstance().updateCart(mListProduct.get(position));
                setUpBadge();
                AppCache.createFile(Cart.getInstance().createJson(Cart.getInstance().getCarts()).toString(),MainActivity.this);
            }
        });
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

        // Ánh xạ layout menu
        MenuItem menuItem = menu.findItem(R.id.itemCart);

        View actionView = menuItem.getActionView();

        mTvCount = actionView.findViewById(R.id.textViewCountBadge);
        mRelativeBackgroundCount = actionView.findViewById(R.id.relativeBadge);
        setUpBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setUpBadge() {
        if(mTvCount != null){
            if(Cart.getInstance().getCarts().size() > 0){
                int count = 0;
                mTvCount.setVisibility(View.VISIBLE);
                mRelativeBackgroundCount.setVisibility(View.VISIBLE);
                for (ElementCart elementCart: Cart.getInstance().getCarts()) {
                    count += elementCart.getQuantity();
                }
                mTvCount.setText(String.valueOf(count));
            }else{
                mTvCount.setVisibility(View.GONE);
                mRelativeBackgroundCount.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpBadge();
    }
}