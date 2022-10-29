package com.project.upendoshop.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.upendoshop.Adapters.CategoriesAdapter;
import com.project.upendoshop.Auth.LoginActivity;
import com.project.upendoshop.Auth.RegisterActivity;
import com.project.upendoshop.Models.CategoriesModel;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView categories;
    CategoriesAdapter categoriesAdapter;
    ArrayList<CategoriesModel> categoriesModels;
    ImageView cartBtn,shopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopping=findViewById(R.id.shop);
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