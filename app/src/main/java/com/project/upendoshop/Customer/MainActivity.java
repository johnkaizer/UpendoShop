package com.project.upendoshop.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.upendoshop.Categories.ClothesActivity;
import com.project.upendoshop.Categories.ElectronicActivity;
import com.project.upendoshop.Categories.FoodActivity;
import com.project.upendoshop.Categories.FruitsActivity;
import com.project.upendoshop.Categories.GroceriesActivity;
import com.project.upendoshop.Models.ProductModel;
import com.project.upendoshop.Adapters.ProductAdapter;
import com.project.upendoshop.R;
import com.project.upendoshop.Categories.ShoesActivity;
import com.project.upendoshop.Categories.StationaryActivity;
import com.project.upendoshop.Categories.ToysActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CardView card1, card2, card3, card4, card5, card6,card7,card8;
    ImageView cartBtn,shopping;

    ProductAdapter productAdapter;
    ArrayList<ProductModel>list;
    RecyclerView prodRec;
    Query databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodRec=findViewById(R.id.products_recycler);
        shopping=findViewById(R.id.shop);
        card1 =findViewById(R.id.c1);
        card2 = findViewById(R.id.c2);
        card3 = findViewById(R.id.c3);
        card4 = findViewById(R.id.c4);
        card5 = findViewById(R.id.c5);
        card6 = findViewById(R.id.c6);
        card7 = findViewById(R.id.c7);
        card8 = findViewById(R.id.c8);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClothesActivity.class));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FruitsActivity.class));
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ToysActivity.class));
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ElectronicActivity.class));
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GroceriesActivity.class));
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShoesActivity.class));
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodActivity.class));
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StationaryActivity.class));
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails");
        prodRec.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager =new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        prodRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false));
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
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        cartBtn=findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item= menu.findItem(R.id.search_item);

        SearchView searchView= (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails").orderByChild("ProductName").startAt(s).endAt(s+"\uf8ff");
        prodRec.setHasFixedSize(true);
        prodRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();
        productAdapter = new ProductAdapter(this, list);
        prodRec.setAdapter(productAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ProductModel productModel= dataSnapshot.getValue(ProductModel.class);
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
