package com.project.upendoshop.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.upendoshop.Admin.AdminActivity;
import com.project.upendoshop.Customer.MainActivity;
import com.project.upendoshop.R;

public class LoginActivity extends AppCompatActivity {
    CheckBox admin;
    private ProgressBar progressBar;
    private EditText EditTextEmail,editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        admin=findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();
        EditTextEmail = findViewById(R.id.editText2);
        editTextPassword = findViewById(R.id.editText3);
        progressBar = findViewById(R.id.progressBar);
    }

    public void sign_Up(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    public void login(View view) {
        if (admin.isChecked()){
            String email1 =EditTextEmail.getText().toString().trim();
            String password1 =editTextPassword.getText().toString().trim();
            if (email1.isEmpty()){
                EditTextEmail.setError(" email is required!!");
                EditTextEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                EditTextEmail.setError("Please provide a valid email address!");
                EditTextEmail.requestFocus();
                return;
            }
            if (password1.isEmpty()){
                editTextPassword.setError(" password is required!!");
                editTextPassword.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            if ("admin@gmail.com".equals(email1) && "abc1234".equals(password1)){
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                Toast.makeText(LoginActivity.this,"Admin Logged in successfully ",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(LoginActivity.this,"Failed to log in check your details ",Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }else
            Login2();
    }

    private void Login2() {
        String email =EditTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            EditTextEmail.setError(" email is required!!");
            EditTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Please provide a valid email address!");
            EditTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError(" password is required!!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to log in check your credentials and Internet connection",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void password(View view) {
        startActivity(new Intent(LoginActivity.this, PasswordActivity.class));

    }
}