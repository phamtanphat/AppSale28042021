package com.example.appsale28042021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsale28042021.R;
import com.example.appsale28042021.adapter.CartAdapter;
import com.example.appsale28042021.interfaces.OnItemClickCartAdapter;
import com.example.appsale28042021.model.Cart;
import com.example.appsale28042021.model.ElementCart;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    TextView mTvTitleTotal,mTvPrice;
    Button mBtnPayment;
    CartAdapter mCartAdapter;
    List<ElementCart> mListCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mToolbar = findViewById(R.id.toolBarCart);
        mRecyclerView = findViewById(R.id.recyclerViewCart);
        mTvPrice = findViewById(R.id.textPrice);
        mTvTitleTotal = findViewById(R.id.textTitleTotal);
        mBtnPayment = findViewById(R.id.payment);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (Cart.getInstance().getCarts() == null || Cart.getInstance().getCarts().size() == 0){
            mTvTitleTotal.setVisibility(View.GONE);
            mTvPrice.setVisibility(View.GONE);
            mBtnPayment.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
        }else{
            mTvTitleTotal.setVisibility(View.VISIBLE);
            mTvPrice.setVisibility(View.VISIBLE);
            mBtnPayment.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        mListCart = Cart.getInstance().getCarts();
        mCartAdapter = new CartAdapter(mListCart);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mCartAdapter);


        mCartAdapter.setOnItemClickCartAdapter(new OnItemClickCartAdapter() {
            @Override
            public void increment(int position) {
                Toast.makeText(CartActivity.this, "Increment " + position , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void decrement(int position) {
                Toast.makeText(CartActivity.this, "decrement " + position , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delete(int position) {
                Toast.makeText(CartActivity.this, "delete " + position , Toast.LENGTH_SHORT).show();
            }
        });
    }
}