package com.project.upendoshop.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Adapters.CustomerOrderAdapter;
import com.project.upendoshop.Adapters.ProductAdapter;
import com.project.upendoshop.Auth.LoginActivity;
import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.Models.ProductModel;
import com.project.upendoshop.Models.User;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Button Logout;
    RecyclerView orders;
    CustomerOrderAdapter customerOrderAdapter;
    ArrayList<CustomerOrderModel>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Logout = findViewById(R.id.sign_out);
        progressBar = findViewById(R.id.progressBar);
        orders= findViewById(R.id.orderRV);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
        reference = FirebaseDatabase.getInstance().getReference().child("OrderDetails");
        orders.setHasFixedSize(true);
        orders.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        customerOrderAdapter = new CustomerOrderAdapter(this,list);
        orders.setAdapter(customerOrderAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    CustomerOrderModel customerOrderModel = dataSnapshot.getValue(CustomerOrderModel.class);
                    list.add(customerOrderModel);
                }
                customerOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progressBar.setVisibility(View.VISIBLE);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullNameTxt = findViewById(R.id.cust_name);
        final TextView emailTxt = findViewById(R.id.cust_email);
        final TextView phoneTxt = findViewById(R.id.cust_phone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String phone = userProfile.phone;

                    fullNameTxt.setText(fullName);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this,"Something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}