package com.example.sellclothesapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sellclothesapp.R;
import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.FragmentHomeBinding;
import com.example.sellclothesapp.model.Bookmark;
import com.example.sellclothesapp.model.Category;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.activity.DetailProductActivity;
import com.example.sellclothesapp.ui.adapter.CategoryAdapter;
import com.example.sellclothesapp.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class HomeFragment extends Fragment implements CategoryAdapter.Callback, ProductAdapter.Callback {
    private FragmentHomeBinding binding;
    private List<Category> categoryList;
    private Controller controller;
    private ProductAdapter productAdapter;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();

    }

    private void initData() {
//        binding.nameUser.setText(User.getInstance().getName());
        controller = new Controller(getActivity());
    }

    private void initView() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "All", R.drawable.ic_all_item));
        categoryList.add(new Category(1, "Paper of all kinds", R.drawable.shirt_sleeveless_svgrepo_com));
        categoryList.add(new Category(1, "Pen-ink", R.drawable.trousers_svgrepo_com));
        categoryList.add(new Category(1, "Notebook", R.drawable.dress_svgrepo_com));
        categoryList.add(new Category(1, "School supplies", R.drawable.dress_svgrepo_com__1_));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, this);
        binding.listCategoryHomeFragment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.listCategoryHomeFragment.setAdapter(categoryAdapter);

        productAdapter = new ProductAdapter(getActivity(), new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                intent.putExtra(AppConstant.TABLE_PRODUCT, product);
                startActivity(intent);
            }
        }, this);

    }

    @Override
    public void callbackCLick(int position) {
        if (position == 0) {
            iniDataListAll();
        } else {
            iniDataListById(position);
        }
    }

    private void iniDataListAll() {
        productAdapter.setData(controller.getAllListProduct());
        binding.listProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.listProduct.setAdapter(productAdapter);
    }

    private void iniDataListById(int id) {
        productAdapter.setData(controller.getListProductByCategory(id));
        binding.listProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.listProduct.setAdapter(productAdapter);
    }

    @Override
    public void deleteBookmark(int id) {
        controller.deleteBookmark(id);
    }

    @Override
    public void addBookmark(int id) {
        controller.addBookmark(new Bookmark(0, id, User.getInstance().getId()));
    }
}