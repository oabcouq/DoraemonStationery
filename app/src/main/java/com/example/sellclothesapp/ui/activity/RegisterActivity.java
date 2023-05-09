package com.example.sellclothesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.ActivityRegisterBinding;
import com.example.sellclothesapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText("Doraemon");
        binding.email.setText("doraemon293@gmail.com");
        binding.phone.setText("0375784487");
        binding.password.setText("1234567");
        binding.address.setText("Viet Nam");
        controller = new Controller(this);
        binding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void register() {
        if (binding.name.getText().toString().isEmpty() || binding.name.getText().toString() == null) {
            Toast.makeText(this, "Name not entered", Toast.LENGTH_SHORT).show();
            return;
        } else if (binding.email.getText().toString().isEmpty() || binding.email.getText().toString() == null) {
            Toast.makeText(this, "No email entered", Toast.LENGTH_SHORT).show();
            return;
        } else if (binding.phone.getText().toString().isEmpty() || binding.phone.getText().toString() == null) {
            Toast.makeText(this, "Phone number not entered", Toast.LENGTH_SHORT).show();
            return;
        } else if (binding.password.getText().toString().isEmpty() || binding.password.getText().toString() == null) {
            Toast.makeText(this, "Password not entered", Toast.LENGTH_SHORT).show();
            return;
        } else if (binding.address.getText().toString().isEmpty() || binding.address.getText().toString() == null) {
            Toast.makeText(this, "Address not entered", Toast.LENGTH_SHORT).show();
            return;
        } else {
            User user = new User(0, binding.name.getText().toString(), binding.email.getText().toString(), binding.phone.getText().toString(), binding.password.getText().toString(), binding.address.getText().toString());
            if (controller.registerUser(user)) {
                Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}