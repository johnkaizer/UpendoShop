package com.project.upendoshop.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {
    ArrayList<CustomerOrderModel>list;
    Context context;

    public AdminOrderAdapter(ArrayList<CustomerOrderModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.order_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(list.get(position).getDate());
        holder.items.setText(list.get(position).getOrderItems());
        holder.name.setText(list.get(position).getCustName());
        holder.location.setText(list.get(position).getLocation());
        holder.phone.setText(list.get(position).getPhoneNumber());
        final String date  = list.get(position).getDate();
        holder.delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[]=new CharSequence[]{
                        // select any from the value
                        "Delete",
                        "Cancel",
                };
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Delete Content");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0) {
                            delete(position,date);

                        }

                    }
                });
                builder.show();
            }
        });

    }

    private void delete(int position, String date) {

        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("OderDetails");

        Query query=dbref.child(date);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at reference
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView items, name, location,phone,date;
        ImageButton delete1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.order_date);
            items= itemView.findViewById(R.id.order_stuffs);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.delivery_location);
            phone = itemView.findViewById(R.id.phone);
            delete1 = itemView.findViewById(R.id.delete);
        }
    }
}
