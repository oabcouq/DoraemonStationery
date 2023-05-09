package com.example.sellclothesapp.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sellclothesapp.R;
import com.example.sellclothesapp.databinding.ItemCardBinding;
import com.example.sellclothesapp.model.Product;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private List<Product> data;
    private Callback callback;

    private double sum = 0;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private HashMap<Product, Integer> cartMap;

    public CardAdapter(List<Product> data) {
        this.data = data;
        this.cartMap = new HashMap<>();
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Product item = data.get(position);
        if (item != null) {
            holder.itemCardBinding.nameUser.setText(item.getName());
            holder.itemCardBinding.content.setText("Size: " + item.getSize() );
            holder.itemCardBinding.price.setText(decimalFormat.format(item.getPrice()) + " vnd");
            RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.img).error(R.drawable.img);
            Glide.with(holder.itemCardBinding.image.getContext()).load(item.getImage()).apply(options).dontAnimate().into(holder.itemCardBinding.image);

            holder.itemCardBinding.down.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.itemCardBinding.count.getText().toString());
                    if (count > 0) {
                        count--;
                        holder.itemCardBinding.count.setText(count + "");
                        if (cartMap.containsKey(item)) {
                            cartMap.replace(item, count);
                            callback.callSum(cartMap);
                        }
                        notifyDataSetChanged();
                    }

                    if (count == 0) {
                        if (cartMap.size() > 0) {
                            cartMap.remove(item);
                            callback.callSum(cartMap);
                        }
                    }
                }
            });

            holder.itemCardBinding.up.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.itemCardBinding.count.getText().toString());
                    count++;
                    holder.itemCardBinding.count.setText(count + "");
                    if (cartMap.containsKey(item)) {
                        cartMap.put(item, count);
                    } else {
                        cartMap.put(item, 1);
                    }
                    notifyDataSetChanged();

                    callback.callSum(cartMap);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCardBinding itemCardBinding;

        public ViewHolder(@NonNull ItemCardBinding itemView) {
            super(itemView.getRoot());
            this.itemCardBinding = itemView;
        }
    }

    public interface Callback {
        void onClick(String id);

        void callSum(HashMap<Product, Integer> cartMap);
    }
}
