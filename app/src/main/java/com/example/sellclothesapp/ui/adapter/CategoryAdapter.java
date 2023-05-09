package com.example.sellclothesapp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellclothesapp.R;
import com.example.sellclothesapp.databinding.ItemCategoryHomefragmentBinding;
import com.example.sellclothesapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;
    private List<Category> data;
    private Callback callback;
    public CategoryAdapter(List<Category> data, Callback callback) {
        this.data = data;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCategoryHomefragmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = data.get(position);
        if (category != null) {
            holder.itemCategoryHomefragmentBinding.image.setImageResource(category.getImage());
            holder.itemCategoryHomefragmentBinding.name.setText(category.getName());

            if (selected) {
                if (position == 0) {
                    callback.callbackCLick(0);
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select);
                    holder.itemCategoryHomefragmentBinding.image.setColorFilter(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.BOLD);
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_select);
                    holder.itemCategoryHomefragmentBinding.image.setColorFilter(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.WHITE);
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.BOLD);
                } else {
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    holder.itemView.setBackgroundResource(R.drawable.back_ground_item_no_select);
                    holder.itemCategoryHomefragmentBinding.image.setColorFilter(Color.GRAY);
                    holder.itemCategoryHomefragmentBinding.name.setTextColor(Color.GRAY);
                    holder.itemCategoryHomefragmentBinding.name.setTypeface(null, Typeface.NORMAL);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    callback.callbackCLick(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryHomefragmentBinding itemCategoryHomefragmentBinding;

        public ViewHolder(ItemCategoryHomefragmentBinding binding) {
            super(binding.getRoot());
            this.itemCategoryHomefragmentBinding = binding;
        }
    }

    public interface Callback {
        void callbackCLick(int position);
    }
}
