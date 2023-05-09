package com.example.sellclothesapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.FragmentCartBinding;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.User;
import com.example.sellclothesapp.ui.activity.PaymentActivity;
import com.example.sellclothesapp.ui.adapter.CardAdapter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CardAdapter.Callback {
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private FragmentCartBinding binding;
    private CardAdapter cardAdapter;
    private Controller controller;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private HashMap<Product, Integer> cartMap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        controller = new Controller(getActivity());

        List<Product> listProduct = controller.getListProductByCardUserId(User.getInstance().getId());

        binding.listCard.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cardAdapter = new CardAdapter(listProduct);
        cardAdapter.setCallback(this);
        binding.listCard.setAdapter(cardAdapter);

        if (listProduct.size() > 0) {
            binding.conentNotNull.setVisibility(View.VISIBLE);
            binding.contentNullCard.setVisibility(View.GONE);
        } else {
            binding.conentNotNull.setVisibility(View.GONE);
            binding.contentNullCard.setVisibility(View.VISIBLE);
        }

        binding.sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartMap != null) {
                    Intent intent = new Intent(getActivity(), PaymentActivity.class);
                    intent.putExtra(AppConstant.CARD_MAP, cartMap);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Please select order quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(String id) {

    }

    @Override
    public void callSum(HashMap<Product, Integer> cartMap) {
        this.cartMap = cartMap;
        binding.countCard.setText("Items (" + cartMap.size() + " items)");
        double sum = 0;
        Set<Map.Entry<Product, Integer>> entries = cartMap.entrySet();
        for (Map.Entry<Product, Integer> entry : entries) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            sum += (key.getPrice() * value);
        }
        binding.countCardPrice.setText(decimalFormat.format(sum) + " vnd");
        binding.sumPrice.setText(decimalFormat.format(sum + (sum * 0.1)) + " vnd");
    }
}