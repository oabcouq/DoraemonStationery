package com.example.sellclothesapp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellclothesapp.R;
import com.example.sellclothesapp.databinding.ItemSizeBinding;
import com.example.sellclothesapp.model.Size;

import java.util.List;
import java.util.function.Consumer;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {
    private List<Size> data;
    private int row_index = -1;
    private boolean selected = true;
    private boolean check = true;

    private Consumer<Integer> callBack;

    public SizeAdapter(List<Size> data) {
        this.data = data;
    }

    public void setCallBack(Consumer<Integer> callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public SizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSizeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Size size = data.get(position);
        if (size != null) {
            holder.binding.name.setText(String.valueOf(size.getName()));

            if (selected) {
                if (position == 0) {
                    holder.binding.name.setTextColor(Color.WHITE);
                    holder.binding.colorBackground.setBackgroundColor(Color.BLACK);
                }
                selected = false;
            } else {
                if (row_index == position) {
                    holder.binding.name.setTextColor(Color.WHITE);
                    holder.binding.colorBackground.setBackgroundColor(Color.BLACK);
                } else {
                    holder.binding.name.setTextColor(holder.binding.colorBackground.getContext().getResources().getColor(R.color.blue_grey_inactive));
                    holder.binding.colorBackground.setBackgroundColor(holder.binding.colorBackground.getContext().getResources().getColor(R.color.colorbackground));
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();
                    callBack.accept(size.getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemSizeBinding binding;

        public ViewHolder(ItemSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
