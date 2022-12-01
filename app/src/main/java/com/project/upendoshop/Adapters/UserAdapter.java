package com.project.upendoshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener =clickListener;
    }

    public UserAdapter(HomeFragment context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.customer_item, parent, false);
        return new ViewHolder(v,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user= list.get(position);
        holder.name.setText(user.getFullName());
        holder.email.setText(user.getEmail());
        holder.password.setText(user.getPassword());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, password;
        ImageButton delete;
        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            name= itemView.findViewById(R.id.cust_name);
            email = itemView.findViewById(R.id.cust_email);
            password = itemView.findViewById(R.id.cust_pass);
            delete = itemView.findViewById(R.id.delete_btn);
        }
    }
}
