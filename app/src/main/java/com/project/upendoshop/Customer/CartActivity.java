package com.project.upendoshop.Customer;

import static com.project.upendoshop.Adapters.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.upendoshop.Adapters.DBmain;
import com.project.upendoshop.Adapters.CartAdapter;
import com.project.upendoshop.Models.CartModels;
import com.project.upendoshop.Activities.PlaceOrderActivity;
import com.project.upendoshop.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView cartrec;
    CartAdapter cartAdapter;
    ArrayList<CartModels> cartModelsList;
    Button order;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        order = findViewById(R.id.btn_order);
        total= findViewById(R.id.total_amount);
        cartrec = findViewById(R.id.cart_recyclerview);
        cartrec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dBmain = new DBmain(this);
        total.setText("" + dBmain.sumPriceCartItems());
        //open order activity
        displayData();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = total.getText().toString();
                startActivity(new Intent(CartActivity.this, PlaceOrderActivity.class));
                Intent intent = new Intent(CartActivity.this, PlaceOrderActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);

            }
        });
    }

    //fetch Cart Data from SQLITE database
    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLENAME + "", null);
        ArrayList<CartModels> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String quantity = cursor.getString(2);
            String pcs = cursor.getString(3);
            String amount = cursor.getString(4);
            list.add(new CartModels(id, title, quantity, pcs, amount));
        }
        cursor.close();
        cartAdapter = new CartAdapter(this, list, sqLiteDatabase, R.layout.cart_items);
        cartrec.setAdapter(cartAdapter);
    }


}