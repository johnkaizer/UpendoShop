package com.project.upendoshop.Activities;

import static com.project.upendoshop.Adapters.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.upendoshop.Adapters.DBmain;
import com.project.upendoshop.R;

public class DetailsActivity extends AppCompatActivity {
    ImageView image;
    TextView name,amount, description, quantity,items;
    ImageButton addItem, removeItem;
    Button AddItems;
    int count=1;
    //SQLITE
    DBmain dBmain;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    int id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dBmain = new DBmain(this);
        image= findViewById(R.id.prod_image);
        name = findViewById(R.id.prod_name);
        amount = findViewById(R.id.prod_price);
        description = findViewById(R.id.prod_desc);
        quantity = findViewById(R.id.prod_quantity);
        items= findViewById(R.id.textView28);
        addItem = findViewById(R.id.add_items);
        removeItem = findViewById(R.id.remove_items);
        AddItems=findViewById(R.id.add_to_cart);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                items.setText(String.valueOf(count));
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                items.setText(String.valueOf(count));
            }
        });
        AddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("title",name.getText().toString());
                cv.put("quantity",quantity.getText().toString());
                cv.put("pcs",items.getText().toString());
                cv.put("amount",amount.getText().toString());
                sqLiteDatabase= dBmain.getWritableDatabase();
                Long recinsert = sqLiteDatabase.insert(TABLENAME,null,cv);
                if (recinsert!= null){
                    Toast.makeText(DetailsActivity.this,"Item Added to Cart successfully",Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });
        name.setText(getIntent().getExtras().getString("ProductName"));
        amount.setText(getIntent().getExtras().getString("ProductPrice"));
        description.setText(getIntent().getExtras().getString("ProductDescription"));
        quantity.setText(getIntent().getExtras().getString("ProductQuantity"));
    }


}