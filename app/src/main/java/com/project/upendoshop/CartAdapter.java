package com.project.upendoshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModels>list;
    SQLiteDatabase sqLiteDatabase;
    int cart_items;

    public CartAdapter(Context context, ArrayList<CartModels> list, SQLiteDatabase sqLiteDatabase, int cart_items) {
        this.context = context;
        this.list = list;
        this.sqLiteDatabase = sqLiteDatabase;
        this.cart_items = cart_items;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final CartModels cartModels = list.get(position);
        holder.item.setText(list.get(position).getTitle());
        holder.amount.setText(list.get(position).getAmount());
        holder.quantity.setText(list.get(position).getQuantity());
        holder.pcs.setText(list.get(position).getPcs());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item,amount,quantity,pcs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item= itemView.findViewById(R.id.prod_title);
            amount=itemView.findViewById(R.id.prod_amount1);
            quantity=itemView.findViewById(R.id.prod_quantity);
            pcs= itemView.findViewById(R.id.prod_pcs);
        }
    }
}
