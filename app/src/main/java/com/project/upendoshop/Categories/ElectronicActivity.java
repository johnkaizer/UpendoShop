package com.project.upendoshop.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Adapters.ProductAdapter;
import com.project.upendoshop.Models.ProductModel;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class ElectronicActivity extends AppCompatActivity {
    ProductAdapter productAdapter;
    ArrayList<ProductModel> list;
    RecyclerView prodRec;
    Query databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);
        prodRec=findViewById(R.id.products_recycler);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails").orderByChild("ProductCategory").startAt("Electronics").endAt("Electronics");
        prodRec.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        prodRec.setLayoutManager(gridLayoutManager);
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(this,list);
        prodRec.setAdapter(productAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                    list.add(productModel);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}