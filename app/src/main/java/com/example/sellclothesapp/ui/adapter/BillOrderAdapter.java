package com.example.sellclothesapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellclothesapp.R;
import com.example.sellclothesapp.dao.Controller;
import com.example.sellclothesapp.databinding.ItemBillBinding;
import com.example.sellclothesapp.model.Bill;
import com.example.sellclothesapp.model.User;

import java.util.List;

public class BillOrderAdapter extends RecyclerView.Adapter<BillOrderAdapter.ViewHodel> {
    private List<Bill> data;
    private boolean isClickSpeed = true;
    private Controller controller;

    public BillOrderAdapter(List<Bill> data, Context context) {
        this.data = data;
        controller = new Controller(context);
    }

    @NonNull
    @Override
    public BillOrderAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodel(ItemBillBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillOrderAdapter.ViewHodel holder, int position) {
        Bill bill = data.get(position);
        if (bill != null) {
            holder.binding.address.setText(bill.getAddress());
            holder.binding.nameUser.setText(bill.getNameUser());
            holder.binding.phone.setText(bill.getPhoneUser());
            holder.binding.price.setText(bill.getSum() +" vnd");
            holder.binding.btnDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isClickSpeed) {
                        holder.binding.btnDown.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                        isClickSpeed = false;
                        holder.binding.contentProduct.setVisibility(View.VISIBLE);
                    } else {
                        holder.binding.btnDown.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                        isClickSpeed = true;
                        holder.binding.contentProduct.setVisibility(View.GONE);
                    }
                }
            });

            holder.binding.listProduct.setLayoutManager(new LinearLayoutManager(holder.binding.address.getContext(), LinearLayoutManager.VERTICAL, false));
            holder.binding.listProduct.setAdapter(new CountProductAdapter(controller.getListProductByListBillAndIdUser(User.getInstance().getId(), bill.getId())));
        }


    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        private ItemBillBinding binding;

        public ViewHodel(@NonNull ItemBillBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
