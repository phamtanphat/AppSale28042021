package com.example.appsale28042021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsale28042021.R;
import com.example.appsale28042021.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> productList;
    Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

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
