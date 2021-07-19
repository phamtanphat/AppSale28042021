package com.example.appsale28042021.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.example.appsale28042021.interfaces.OnItemClickAdapter;
import com.example.appsale28042021.model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    List<Product> productList;
    List<Product> productListOld;
    Context context;
    int width;
    int height;
    OnItemClickAdapter onItemClickAdapter;
    Handler handler1;
    boolean isPressed = false;

    public ProductAdapter(List<Product> productList, Context context, int width, int height) {
        this.productList = productList;
        productListOld = new ArrayList<>();
        productListOld.addAll(productList);
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_item_product, parent, false);

        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) (width * 0.45);
        layoutParams.height = (int) (height * 0.35);
        layoutParams.setMargins((int) (width * 0.025), 0, 0, (int) (width * 0.05));

        view.setLayoutParams(layoutParams);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);


        if (product.getSaleOf() <= 0) {
            holder.imgIconSale.setVisibility(View.GONE);
        } else {
            holder.imgIconSale.setVisibility(View.VISIBLE);
        }

        holder.imgProduct.setPadding((int) (width * 0.03), (int) (width * 0.03), (int) (width * 0.03), (int) (width * 0.03));
        holder.imgProduct.setImageResource(product.getImage());
        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtPrice.setText(formatter.format(product.getPrice()) + " Đ");
        holder.txtName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();

                if (strSearch.isEmpty()) {
                    productList = productListOld;
                } else {
                    List<Product> productFilter = new ArrayList<>();
                    for (Product product : productListOld) {
                        if (product.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            productFilter.add(product);
                        }
                    }
                    productList = productFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productList = (List<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgProduct, imgIconSale;
        Button btnBuy;
        TextView txtName, txtPrice;

        @SuppressLint("ClickableViewAccessibility")
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            imgProduct = itemView.findViewById(R.id.imageViewProduct);
            imgIconSale = itemView.findViewById(R.id.imageViewIconSale);
            btnBuy = itemView.findViewById(R.id.buttonBuy);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPrice = itemView.findViewById(R.id.textViewPrice);


            btnBuy.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isPressed = true;
                            break;
                        case MotionEvent.ACTION_UP:
                        default:
                            isPressed = false;
                    }
                    handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isPressed) {
                                onItemClickAdapter.onClick(getAdapterPosition());
                                if (handler1 != null) {
                                    handler1.postDelayed(this, 100);
                                }

                            } else {
                                if (handler1 != null) {
                                    handler1 = null;
                                }
                            }
                        }
                    }, 100);

                    return false;
                }
            });
        }
    }

    public void setOnItemClickAdapter(OnItemClickAdapter onItemClickAdapter) {
        this.onItemClickAdapter = onItemClickAdapter;
    }
}
