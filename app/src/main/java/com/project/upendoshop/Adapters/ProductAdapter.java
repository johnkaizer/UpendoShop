package com.project.upendoshop.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.upendoshop.Activities.DetailsActivity;
import com.project.upendoshop.Models.ProductModel;
import com.project.upendoshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductModel>list;

    public ProductAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductModel productModel= list.get(position);
        holder.price.setText(productModel.getProductAmount());
        holder.name.setText(productModel.getProductName());
        holder.quantity.setText(productModel.getProductQuantity());
        Picasso.get().load(productModel.getImageUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailsActivity.class);
                intent.putExtra("ProductImage",list.get(position).getImageUrl());
                intent.putExtra("ProductPrice",list.get(position).getProductAmount());
                intent.putExtra("ProductName",list.get(position).getProductName());
                intent.putExtra("ProductQuantity",list.get(position).getProductQuantity());
                intent.putExtra("ProductDescription",list.get(position).getProductDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price,quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView7);
            name = itemView.findViewById(R.id.textView15);
            price = itemView.findViewById(R.id.textView16);
            quantity= itemView.findViewById(R.id.quantity);
        }
    }
}
