package com.project.upendoshop.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Adapters.CategoriesAdapter;
import com.project.upendoshop.Models.CategoriesModel;
import com.project.upendoshop.Models.ProductModel;
import com.project.upendoshop.Models.User;
import com.project.upendoshop.ProductAdapter;
import com.project.upendoshop.R;
import com.project.upendoshop.UserAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView categories;
    CategoriesAdapter categoriesAdapter;
    ArrayList<CategoriesModel> categoriesModels;
    ImageView cartBtn,shopping;

    ProductAdapter productAdapter;
    ArrayList<ProductModel>list;
    RecyclerView prodRec;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodRec=findViewById(R.id.products_recycler);
        shopping=findViewById(R.id.shop);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails");
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
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        cartBtn=findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        categories= findViewById(R.id.categories);
        categoriesModels= new ArrayList<>();

        categoriesModels.add(new CategoriesModel(R.drawable.groceries,"Groceries"));
        categoriesModels.add(new CategoriesModel(R.drawable.clothes,"Clothes"));
        categoriesModels.add(new CategoriesModel(R.drawable.shoes,"Shoes"));
        categoriesModels.add(new CategoriesModel(R.drawable.foodstuffs,"Foodstuffs"));
        categoriesModels.add(new CategoriesModel(R.drawable.electronic,"Electronics"));
        categoriesModels.add(new CategoriesModel(R.drawable.fruits,"Fruits"));
        categoriesModels.add(new CategoriesModel(R.drawable.stationery,"Stationery"));
        categoriesModels.add(new CategoriesModel(R.drawable.toys,"Toys"));
        categoriesModels.add(new CategoriesModel(R.drawable.more,"More Products"));

        categoriesAdapter = new CategoriesAdapter(this, categoriesModels, this);
        categories.setAdapter(categoriesAdapter);
        categories.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false));
        categories.setHasFixedSize(true);
        categories.setNestedScrollingEnabled(false);

    }

}