package com.project.upendoshop.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.PaymentActivity;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.ViewHolder> {
    public CustomerOrderAdapter(Context context, ArrayList<CustomerOrderModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<CustomerOrderModel>list;
    @NonNull
    @Override
    public CustomerOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_order_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(list.get(position).getDate());
        holder.items.setText(list.get(position).getOrderItems());
        holder.amount.setText(list.get(position).getOrderAmount());
        holder.location.setText(list.get(position).getLocation());
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("amount",list.get(position).getOrderAmount());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,items,location,amount;
        Button cancel,pay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date= itemView.findViewById(R.id.order_date);
            items= itemView.findViewById(R.id.order_stuffs);
            location= itemView.findViewById(R.id.delivery_location);
            amount= itemView.findViewById(R.id.delivery_amount);
            cancel= itemView.findViewById(R.id.delete);
            pay = itemView.findViewById(R.id.pay);


        }
    }
}
