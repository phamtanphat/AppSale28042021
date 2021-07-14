package com.example.appsale28042021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsale28042021.R;
import com.example.appsale28042021.model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> productList;
    Context context;
    int width;
    int height;

    public ProductAdapter(List<Product> productList, Context context , int width , int height) {
        this.productList = productList;
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_item_product,parent,false);

        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) (width * 0.45);
        layoutParams.height = (int) (height * 0.35);
        layoutParams.setMargins((int) (width * 0.025) , 0 , 0 ,(int) (width * 0.05));

        view.setLayoutParams(layoutParams);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);


        if (product.getSaleOf() <= 0){
            holder.imgIconSale.setVisibility(View.GONE);
        }else{
            holder.imgIconSale.setVisibility(View.VISIBLE);
        }

        holder.imgProduct.setPadding((int) (width * 0.03),(int) (width * 0.03),(int) (width * 0.03),(int) (width * 0.03));
        holder.imgProduct.setImageResource(product.getImage());
        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtPrice.setText(formatter.format(product.getPrice()) + " Đ");
        holder.txtName.setText(product.getName());
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
