package com.example.sellclothesapp.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellclothesapp.databinding.ItemColorBinding;
import com.example.sellclothesapp.model.Color;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private List<Color> data;
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;

    public ColorAdapter(List<Color> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemColorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Color color = data.get(position);
        if (color != null) {
            holder.binding.colorBackground.setBackgroundColor(color.getColor());

            if (selected) {
                if (position == 0) {
                    holder.binding.icon.setVisibility(View.VISIBLE);
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.binding.icon.setVisibility(View.VISIBLE);
                } else {
                    holder.binding.icon.setVisibility(View.GONE);
                }
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
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
        private ItemColorBinding binding;

        public ViewHolder(ItemColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
