package com.example.MobileStore;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MobileStore.databinding.RecyclerItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public static Context context;
    private List<Product> dataList;

    public void setSearchList(List<Product> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<Product>dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding recyclerItemBinding = RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MyViewHolder(recyclerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder{

    RecyclerItemBinding binding;
    int count = 1;

    public MyViewHolder(RecyclerItemBinding itemView){
        super(itemView.getRoot());
        binding = itemView;

    }
    void setData(Product dataClass) {
        if (dataClass.name.length() > 50) {
            binding.recTitle.setText(dataClass.name.substring(0, 50));
        } else {
            binding.recTitle.setText(dataClass.name);
        }
        binding.recDesc.setText(dataClass.price);
        if (dataClass.image != null) {
            Picasso.get().load(dataClass.image).into(binding.recImage);
        }
        binding.appCompatButton.setOnClickListener(v -> {
        });
        binding.recCard.setOnClickListener(v -> {
            Intent intent = new Intent(MyAdapter.context, DetailActivity.class);
            intent.putExtra("productUrl", dataClass.productUrl);
            intent.putExtra("productPrice", dataClass.price);
            intent.putExtra("image", dataClass.image);
            intent.putExtra("productName", dataClass.name);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyAdapter.context.startActivity(intent);
        });
    }
}
