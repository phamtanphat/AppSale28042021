package com.example.appsale28042021.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.appsale28042021.R;
import com.example.appsale28042021.interfaces.OnItemClickCartAdapter;
import com.example.appsale28042021.model.ElementCart;
import com.example.appsale28042021.model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<ElementCart> mCartList;

    private ViewBinderHelper mViewBinderHelper;
    private OnItemClickCartAdapter mOnItemClickCartAdapter;

    public CartAdapter(List<ElementCart> mCartList) {
        this.mCartList = mCartList;
        mViewBinderHelper = new ViewBinderHelper();
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

        mViewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(product.getId()));

        holder.imgCart.setImageResource(product.getImage());
        holder.tvName.setText(product.getName());
        NumberFormat formatter = new DecimalFormat("#,###");
        if (product.getSaleOf() > 0){
            holder.tvPrice.setText(formatter.format((product.getPrice() * ((100 - product.getSaleOf()) / 100) )) + " Đ");
            holder.tvPriceSale.setText(Html.fromHtml( "<del>"+formatter.format(product.getPrice())+" Đ</del>"+ " "  + String.valueOf(product.getSaleOf()).replace(".0"," %")));
        }else{
            holder.tvPrice.setText(formatter.format((product.getPrice())));
            holder.tvPriceSale.setVisibility(View.GONE);
        }

        if (elementCart.getQuantity() <= 1){
            holder.imgDeCrease.setVisibility(View.GONE);
        }


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

            imgDeCrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickCartAdapter != null){
                        mOnItemClickCartAdapter.decrement(getAdapterPosition());
                    }
                }
            });

            imgInCrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickCartAdapter != null){
                        mOnItemClickCartAdapter.increment(getAdapterPosition());
                    }
                }
            });

            layoutDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickCartAdapter != null){
                        mOnItemClickCartAdapter.delete(getAdapterPosition());
                    }
                }
            });


        }
    }
    public void setOnItemClickCartAdapter(OnItemClickCartAdapter onItemClickCartAdapter){
        mOnItemClickCartAdapter = onItemClickCartAdapter;
    }
}
