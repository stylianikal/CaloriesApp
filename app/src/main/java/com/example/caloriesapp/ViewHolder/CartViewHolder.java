package com.example.caloriesapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriesapp.Interface.ItemClickListner;
import com.example.caloriesapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductCalories, txtProductQuantity;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView)
    {
        super(itemView);
        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductCalories = itemView.findViewById(R.id.cart_product_calories);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);


    }

    @Override
    public void onClick(View view)
    {
        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
}
