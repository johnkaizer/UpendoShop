package com.project.upendoshop.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.project.upendoshop.Admin.AdminActivity;
import com.project.upendoshop.Customer.MainActivity;
import com.project.upendoshop.R;

public class LoginActivity extends AppCompatActivity {
    CheckBox admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        admin=findViewById(R.id.checkBox);
    }

    public void sign_Up(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    public void login(View view) {
        if (admin.isChecked()){
            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            finish();

        }else
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}