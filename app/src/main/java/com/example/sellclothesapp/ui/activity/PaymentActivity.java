package com.example.sellclothesapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.ActivityPaymentBinding;
import com.example.sellclothesapp.model.Bill;
import com.example.sellclothesapp.model.BillInFo;
import com.example.sellclothesapp.model.Payment;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.adapter.PaymentAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PaymentActivity extends AppCompatActivity implements PaymentAdapter.Callback {

    private List<Payment> listPayment;
    private ActivityPaymentBinding binding;
    private HashMap<Product, Integer> cartMap ;
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private int payment = 0;
    private double sum = 0;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initToolbar();
        initView();

    }

    private void initToolbar() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        controller = new Controller(this);
        cartMap = (HashMap<Product, Integer>) getIntent().getSerializableExtra(AppConstant.CARD_MAP);
        binding.name.setText(User.getInstance().getName());
        binding.phone.setText(User.getInstance().getPhone());
        binding.address.setText(User.getInstance().getAddress());

        listPayment = new ArrayList<>();
        listPayment.add(new Payment(1, "Payment to receive goods"));
        listPayment.add(new Payment(2, "Stripe"));
        listPayment.add(new Payment(3, "MoMo"));
        listPayment.add(new Payment(4, "VISA"));
        binding.listPayment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PaymentAdapter paymentAdapter = new PaymentAdapter(listPayment);
        paymentAdapter.setCallback(this);
        binding.listPayment.setAdapter(paymentAdapter);

        if (cartMap != null) {
            Set<Map.Entry<Product, Integer>> entries = cartMap.entrySet();
            for (Map.Entry<Product, Integer> entry : entries) {
                Product key = entry.getKey();
                Integer value = entry.getValue();
                sum += (key.getPrice() * value);
            }
            binding.countProduct.setText(cartMap.size() + " product");
            binding.priceNotTax.setText(decimalFormat.format(sum) + " vnd");
            binding.tax.setText((sum * 0.1) + " vnd");
            binding.sumPrice.setText(sum + (sum * 0.1) + " vnd");
        }
        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Random random = new Random();
                    final int randomNumber = random.nextInt(9999);
                    Bill bill = new Bill(randomNumber, User.getInstance().getId(), binding.name.getText().toString(), binding.phone.getText().toString(), binding.address.getText().toString(), payment, (int) sum);
                    if (controller.addBill(bill)) {
                        Set<Map.Entry<Product, Integer>> entries = cartMap.entrySet();
                        for (Map.Entry<Product, Integer> entry : entries) {
                            Product key = entry.getKey();
                            Integer value = entry.getValue();
                            controller.addBillInfo(new BillInFo("0", randomNumber, User.getInstance().getId(), key.getId(), value));
                        }
                        Intent intent = new Intent(PaymentActivity.this, OrderSuccessActivity.class);
                        intent.putExtra(AppConstant.TABLE_BILL, String.valueOf(randomNumber));
                        startActivity(intent);
                    } else {
                        Toast.makeText(PaymentActivity.this, "Purchase failure", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    @Override
    public void callbackPosition(int id) {
        payment = id;
    }
}