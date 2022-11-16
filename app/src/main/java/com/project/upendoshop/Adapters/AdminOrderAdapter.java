package com.project.upendoshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {

    public AdminOrderAdapter(ArrayList<CustomerOrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    ArrayList<CustomerOrderModel>list;
    Context context;
    @NonNull
    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getDate());
        holder.items.setText(list.get(position).getOrderItems());
        holder.name.setText(list.get(position).getCustName());
        holder.location.setText(list.get(position).getLocation());
        holder.phone.setText(list.get(position).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView items, name, location,phone,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.order_date);
            items= itemView.findViewById(R.id.order_stuffs);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.delivery_location);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
