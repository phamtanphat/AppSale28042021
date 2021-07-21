package com.example.appsale28042021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appsale28042021.R;
import com.example.appsale28042021.model.Cart;

public class CartActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    TextView mTvTitleTotal,mTvPrice;
    Button mBtnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mToolbar = findViewById(R.id.toolBarCart);
        mRecyclerView = findViewById(R.id.recyclerViewCart);
        mTvPrice = findViewById(R.id.textPrice);
        mTvTitleTotal = findViewById(R.id.textTitleTotal);
        mBtnPayment = findViewById(R.id.payment);

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
    }
}