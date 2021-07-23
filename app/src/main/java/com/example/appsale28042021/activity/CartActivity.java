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
import com.example.appsale28042021.shared.AppCache;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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


        checkVisiblePayment();
        updatePrice();

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBtnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCache.deleteFile(CartActivity.this);
                Toast.makeText(CartActivity.this, "Thanh toán thành công!!", Toast.LENGTH_SHORT).show();
                Cart.getInstance().setListCart(null);
                finish();
            }
        });


        mListCart = Cart.getInstance().getCarts();
        mCartAdapter = new CartAdapter(mListCart);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mCartAdapter);


        mCartAdapter.setOnItemClickCartAdapter(new OnItemClickCartAdapter() {
            @Override
            public void increment(int position) {
                ElementCart elementCart = Cart.getInstance().getCarts().get(position);
                elementCart.setQuantity(elementCart.getQuantity() + 1);
                mCartAdapter.notifyItemChanged(position);
                checkVisiblePayment();
                updatePrice();
                AppCache.createFile(Cart.getInstance().createJson(Cart.getInstance().getCarts()).toString(),CartActivity.this);
            }

            @Override
            public void decrement(int position) {
                ElementCart elementCart = Cart.getInstance().getCarts().get(position);
                elementCart.setQuantity(elementCart.getQuantity() - 1);
                mCartAdapter.notifyItemChanged(position);
                checkVisiblePayment();
                updatePrice();
                AppCache.createFile(Cart.getInstance().createJson(Cart.getInstance().getCarts()).toString(),CartActivity.this);
            }

            @Override
            public void delete(int position) {
                Cart.getInstance().getCarts().remove(position);
                mCartAdapter.notifyItemRemoved(position);
                checkVisiblePayment();
                updatePrice();
                AppCache.createFile(Cart.getInstance().createJson(Cart.getInstance().getCarts()).toString(),CartActivity.this);
            }
        });
    }
    private void checkVisiblePayment(){
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

    private void updatePrice(){
        NumberFormat formatter = new DecimalFormat("#,###");
        mTvPrice.setText(formatter.format(Cart.getInstance().totalCart()) + " VNĐ");
    }

}