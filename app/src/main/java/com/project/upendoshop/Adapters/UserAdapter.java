package com.project.upendoshop.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.upendoshop.Admin.HomeFragment;
import com.project.upendoshop.Models.User;
import com.project.upendoshop.R;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    HomeFragment context;
    ArrayList<User>list;

    public UserAdapter(HomeFragment context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user= list.get(position);
        holder.name.setText(user.getFullName());
        holder.email.setText(user.getEmail());
        holder.password.setText(user.getPassword());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.cust_name);
            email = itemView.findViewById(R.id.cust_email);
            password = itemView.findViewById(R.id.cust_pass);
        }
    }
}
