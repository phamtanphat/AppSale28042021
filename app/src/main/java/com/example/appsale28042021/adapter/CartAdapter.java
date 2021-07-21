package com.example.appsale28042021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.appsale28042021.R;
import com.example.appsale28042021.model.ElementCart;
import com.example.appsale28042021.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<ElementCart> mCartList;

    public CartAdapter(List<ElementCart> mCartList) {
        this.mCartList = mCartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ElementCart elementCart = mCartList.get(position);
        Product product = elementCart.getProduct();

        holder.imgCart.setImageResource(product.getImage());
//        holder
    }

    @Override
    public int getItemCount() {
        return mCartList != null && mCartList.size() > 0 ? mCartList.size() : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCart, imgInCrease, imgDeCrease;
        private TextView tvName, tvPrice, tvPriceSale, tvAmount;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvPriceSale = itemView.findViewById(R.id.tv_price_sale_cart);
            tvAmount = itemView.findViewById(R.id.editTextAmount);
            imgInCrease = itemView.findViewById(R.id.imageViewInCrease);
            imgDeCrease = itemView.findViewById(R.id.imageViewDeCrease);
            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
        }
    }
}
