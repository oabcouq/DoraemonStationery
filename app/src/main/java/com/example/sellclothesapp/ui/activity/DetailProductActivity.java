package com.example.sellclothesapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sellclothesapp.R;
import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.ActivityDetailProductBinding;
import com.example.sellclothesapp.model.Bookmark;
import com.example.sellclothesapp.model.Card;
import com.example.sellclothesapp.model.Color;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.Size;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.adapter.ColorAdapter;
import com.example.sellclothesapp.ui.adapter.SizeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DetailProductActivity extends AppCompatActivity {
    private boolean isClickSpeed = true;
    private ActivityDetailProductBinding binding;
    private List<Color> listColor;
    private List<Size> listSize;
    private Product product;
    private Controller controller;
    private Bookmark bookmark;
    private int size = 38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniData();
        initView();
    }

    private void iniData() {
        product = (Product) getIntent().getSerializableExtra(AppConstant.TABLE_PRODUCT);
    }

    private void initView() {
        controller = new Controller(this);
        listColor = new ArrayList<>();
        listColor.add(new Color(1, -16777216));
        listColor.add(new Color(2, android.graphics.Color.GREEN));
        listColor.add(new Color(3, android.graphics.Color.RED));
        listColor.add(new Color(4, android.graphics.Color.GRAY));
        binding.listColor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ColorAdapter colorAdapter = new ColorAdapter(listColor);

        binding.listColor.setAdapter(colorAdapter);

        listSize = new ArrayList<>();
        listSize.add(new Size(1, 38));
        listSize.add(new Size(2, 39));
        listSize.add(new Size(3, 40));
        listSize.add(new Size(4, 41));

        binding.listSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SizeAdapter sizeAdapter = new SizeAdapter(listSize);
        sizeAdapter.setCallBack(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                size = integer;
            }
        });
        binding.listSize.setAdapter(sizeAdapter);

        binding.name.setText(product.getName());
        binding.price.setText(product.getPrice() + " vnd");
        binding.more.setText(product.getMore());
        binding.countFeedback.setText(product.getCountFeedback() + " assess");
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.img).error(R.drawable.img);
        Glide.with(this).load(product.getImage()).apply(options).dontAnimate().into(binding.image);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bookmark = controller.getBookmarkByIdUserAndIdProduct(product.getId(), User.getInstance().getId());
        if (bookmark != null) {
            binding.imageFavor.setImageResource(R.drawable.ic_favorite_full);
            isClickSpeed = false;
        } else {
            binding.imageFavor.setImageResource(R.drawable.ic_favorite_border_24);
            isClickSpeed = true;
        }

        Card card = controller.getCardByUserId(product.getId(), User.getInstance().getId());
        if (card != null) {
            binding.sumit.setText("Products already in the cart");
            binding.sumit.setEnabled(false);
        } else {
            binding.sumit.setText("+ Add a shopping cart");
            binding.sumit.setEnabled(true);
        }

        binding.btnFavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickSpeed) {
                    binding.imageFavor.setImageResource(R.drawable.ic_favorite_full);
                    isClickSpeed = false;
                    controller.addBookmark(new Bookmark(0, product.getId(), User.getInstance().getId()));
                } else {
                    controller.deleteBookmark(bookmark.getId());
                    binding.imageFavor.setImageResource(R.drawable.ic_favorite_border_24);
                    isClickSpeed = true;
                }
            }
        });

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card(0, User.getInstance().getId(), product.getId(), size, 0);
                if (controller.addToCard(card)) {
                    binding.sumit.setText("Products already in the cart");
                    binding.sumit.setEnabled(false);
                    Toast.makeText(DetailProductActivity.this, "Add to cart success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailProductActivity.this, "Add to cart success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}