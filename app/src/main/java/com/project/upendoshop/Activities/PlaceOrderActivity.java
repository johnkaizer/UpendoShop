package com.project.upendoshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.project.upendoshop.Adapters.DBmain;
import com.project.upendoshop.Customer.CartActivity;
import com.project.upendoshop.Customer.MainActivity;
import com.project.upendoshop.Models.CustomerOrderModel;
import com.project.upendoshop.R;

import java.text.DateFormat;
import java.util.Calendar;

public class PlaceOrderActivity extends AppCompatActivity {
    TextView items,date,amount1;
    DBmain dBmain;
    EditText location,name, phone;
    DatabaseReference dataRef;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        items= findViewById(R.id.items_20);
        dBmain = new DBmain(this);
        items.setText("" + dBmain.CartItems());
        date= findViewById(R.id.order_date);
        amount1 = findViewById(R.id.order_amount);
        location = findViewById(R.id.cust_location);
        name = findViewById(R.id.cust_name);
        phone = findViewById(R.id.cust_phone);
        save=findViewById(R.id.btn_place_order);
        //getting local date and setting
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        date.setText(currentDate);
        //getting amount and setting
        String amount = getIntent().getStringExtra("amount");
        amount1.setText(amount);
        dataRef= FirebaseDatabase.getInstance().getReference().child("OrderDetails");
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceOrder();
            }
        });
    }

    private void PlaceOrder() {
        String orderDate =  date.getText().toString();
        String orderItem =  items.getText().toString();
        String orderAmount =  amount1.getText().toString();
        String custLocation =  location.getText().toString();
        String custName =  name.getText().toString();
        String custPhone =  phone.getText().toString();

        CustomerOrderModel order = new CustomerOrderModel(orderItem,orderAmount,orderDate,custName,custPhone,custLocation);
        dataRef.push().setValue(order);
        Toast.makeText(PlaceOrderActivity.this,"Order Placed Successfully",Toast.LENGTH_SHORT);
        startActivity(new Intent(PlaceOrderActivity.this, MainActivity.class));
        finish();
    }
}