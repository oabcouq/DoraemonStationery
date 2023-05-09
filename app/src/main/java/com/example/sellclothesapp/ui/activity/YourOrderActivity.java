package com.example.sellclothesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sellclothesapp.MainActivity;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.ActivityYourOrderBinding;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.adapter.BillOrderAdapter;

public class YourOrderActivity extends AppCompatActivity {
    private ActivityYourOrderBinding binding;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYourOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourOrderActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        controller = new Controller(this);
        BillOrderAdapter billOrderAdapter = new BillOrderAdapter(controller.getBillByIdUser(User.getInstance().getId()), this);

        binding.yourOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.yourOrder.setAdapter(billOrderAdapter);
    }
}