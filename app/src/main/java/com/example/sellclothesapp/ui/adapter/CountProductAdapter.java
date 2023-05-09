package com.example.sellclothesapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellclothesapp.databinding.ItemBillProductBinding;
import com.example.sellclothesapp.model.Product;

import java.util.List;

public class CountProductAdapter extends RecyclerView.Adapter<CountProductAdapter.ViewHodel> {
    private List<Product> data;

    public CountProductAdapter(List<Product> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CountProductAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodel(ItemBillProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountProductAdapter.ViewHodel holder, int position) {
        Product item = data.get(position);
        if (item != null) {
            holder.binding.countProduct.setText(item.getCountProduct() + " product");
            holder.binding.nameProduct.setText(item.getName());
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        private ItemBillProductBinding binding;

        public ViewHodel(@NonNull ItemBillProductBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
