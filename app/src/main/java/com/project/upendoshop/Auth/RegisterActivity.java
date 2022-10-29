package com.project.upendoshop.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.upendoshop.Customer.MainActivity;
import com.project.upendoshop.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void home(View view) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    public void sign_In(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}