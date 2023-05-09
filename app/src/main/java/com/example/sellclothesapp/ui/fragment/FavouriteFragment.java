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

import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.FragmentFavouriteBinding;
import com.example.sellclothesapp.model.Bookmark;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.activity.DetailProductActivity;
import com.example.sellclothesapp.ui.adapter.ProductAdapter;

import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment implements ProductAdapter.Callback {
    private ProductAdapter productAdapter;
    private Controller controller;
    private FragmentFavouriteBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        controller = new Controller(getActivity());
        productAdapter = new ProductAdapter(getActivity(), new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                Intent intent = new Intent(getActivity(), DetailProductActivity.class);
                intent.putExtra(AppConstant.TABLE_PRODUCT, product);
                startActivity(intent);
            }
        }, this);
        productAdapter.setData(controller.getAllListProductBookmarkById(User.getInstance().getId()));
        binding.listBookmark.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.listBookmark.setAdapter(productAdapter);

        if (controller.getAllListProductBookmarkById(User.getInstance().getId()).size() > 0) {
            binding.listBookmark.setVisibility(View.VISIBLE);
            binding.contentNullCard.setVisibility(View.GONE);
        } else {
            binding.listBookmark.setVisibility(View.GONE);
            binding.contentNullCard.setVisibility(View.VISIBLE);
        }
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