package com.example.MobileStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends AppCompatActivity {

    List<Product> dataList;
    List<Product> phones;
    List<Product> tablets;
    List<Product> computers;


    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAll);
        SearchView searchView = findViewById(R.id.searchAll);

        searchView.clearFocus();

        dataList = new ArrayList<>();
        computers = new ArrayList<>();
        phones = new ArrayList<>();
        tablets = new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
        adapter = new MyAdapter(AllActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(AllActivity.this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        getProducts();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setVisibility(View.VISIBLE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList4(newText);
                return true;
            }
        });
    }

    private void getProducts() {
        getComputer2();
        getTablets2();
        getPhones2();
        Toast.makeText(AllActivity.this, "All products are loaded", Toast.LENGTH_SHORT).show();
    }

    private void getPhones2() {
        FirebaseFirestore.getInstance().collection("phones")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    phones.clear();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Product product = new Product();
                        product.name = documentSnapshot.getString("name");
                        product.price = documentSnapshot.getString("price");
                        product.image = documentSnapshot.getString("image");
                        product.productId = documentSnapshot.getId();
                        product.productUrl = documentSnapshot.getString("productUrl");
                        phones.add(product);
                    }
                    dataList.addAll(phones);
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AllActivity.this, "Cant load data", Toast.LENGTH_SHORT).show();
                });

    }

    private void getTablets2() {
        FirebaseFirestore.getInstance().collection("tablets")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    tablets.clear();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Product product = new Product();
                        product.name = documentSnapshot.getString("name");
                        product.price = documentSnapshot.getString("price");
                        product.image = documentSnapshot.getString("image");
                        product.productId = documentSnapshot.getId();
                        product.productUrl = documentSnapshot.getString("productUrl");
                        tablets.add(product);
                    }
                    dataList.addAll(tablets);
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AllActivity.this, "Cant load data", Toast.LENGTH_SHORT).show();
                });

    }

    private void getComputer2() {
        FirebaseFirestore.getInstance().collection("computers")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    computers.clear();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Product product = new Product();
                        product.name = documentSnapshot.getString("name");
                        product.price = documentSnapshot.getString("price");
                        product.image = documentSnapshot.getString("image");
                        product.productId = documentSnapshot.getId();
                        product.productUrl = documentSnapshot.getString("productUrl");
                        computers.add(product);
                    }
                    dataList.addAll(computers);
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AllActivity.this, "Cant load data", Toast.LENGTH_SHORT).show();
                });
    }


    private void searchList4(String text) {
        List<Product> dataSearchList = new ArrayList<>();
        for (Product data : dataList) {
            if (data.name.toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (!dataSearchList.isEmpty()) {
            adapter.setSearchList(dataSearchList);
        } else {
            Toast.makeText(AllActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}