package com.project.upendoshop.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Adapters.AdminOrderAdapter;
import com.project.upendoshop.Adapters.UserAdapter;
import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.Models.User;
import com.project.upendoshop.R;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdminOrderAdapter adminOrderAdapter;
    ArrayList<CustomerOrderModel>list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("OrderDetails");
        recyclerView = view.findViewById(R.id.orders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        adminOrderAdapter = new AdminOrderAdapter(list, getContext());
        recyclerView.setAdapter(adminOrderAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    CustomerOrderModel customerOrderModel = dataSnapshot.getValue(CustomerOrderModel.class);
                    list.add(customerOrderModel);
                }
                adminOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}