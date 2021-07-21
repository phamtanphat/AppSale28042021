package com.example.appsale28042021.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.appsale28042021.R;

public class CartAdapter {

    class CartViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgCart,imgInCrease,imgDeCrease;
        private TextView tvName,tvPrice,tvAmount;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvAmount = itemView.findViewById(R.id.editTextAmount);
            imgInCrease = itemView.findViewById(R.id.imageViewInCrease);
            imgDeCrease = itemView.findViewById(R.id.imageViewDeCrease);
            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
        }
    }
}
