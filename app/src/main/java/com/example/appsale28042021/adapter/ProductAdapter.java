package com.example.appsale28042021.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsale28042021.R;

public class ProductAdapter {

    class ProductViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imgProduct , imgIconSale;
        Button btnBuy;
        TextView txtName,txtPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            imgProduct = itemView.findViewById(R.id.imageViewProduct);
            imgIconSale = itemView.findViewById(R.id.imageViewIconSale);
            btnBuy = itemView.findViewById(R.id.buttonBuy);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
