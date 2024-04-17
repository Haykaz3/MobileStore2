package com.example.MobileStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.MobileStore.databinding.ActivityDetailBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private com.example.MobileStore.databinding.ActivityDetailBinding binding;
    private String productUrl;
    private String productPrice;
    private String productName;
    private String image;
    List<String> strings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        productUrl = getIntent().getStringExtra("productUrl");
        productPrice = getIntent().getStringExtra("productPrice");
        productName = getIntent().getStringExtra("productName");
        image = getIntent().getStringExtra("image");
        binding.detailPrice.setText(productPrice);
        binding.productNameDetail.setText(productName);
        binding.productNameDetail4.setText(productName);
        Picasso.get().load(image).into(binding.detailImage);
        binding.mobileCentreRef.setOnClickListener(v -> {
            Uri uri = Uri.parse(productUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        new FetchTask1().execute();
    }
    private class FetchTask1 extends AsyncTask<Void, Void, Void> {
        String url = productUrl;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(url).get();

                for (int k = 1; k <= 7; k++) {
                    int i = 2, j = i + 1;
                    while (doc.selectFirst("body > div:nth-child(10) > div > div:nth-child(" + k + ") > div:nth-child(" + i + ")") != null && doc.selectFirst("body > div:nth-child(10) > div > div:nth-child(1) > div:nth-child(" + j + ")") != null) {
                        Element productDescName = doc.selectFirst("body > div:nth-child(10) > div > div:nth-child(" + k + ") > div:nth-child(" + i + ")");
                        Element productDescValue = doc.selectFirst("body > div:nth-child(10) > div > div:nth-child(" + k + ") > div:nth-child(" + j + ")");
                        strings.add(productDescName.text() + " " + productDescValue.text() + "\n");
                        i+=2;
                        j+=2;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            binding.detailDescription.setText(String.join(" ",strings));
        }
    }
}