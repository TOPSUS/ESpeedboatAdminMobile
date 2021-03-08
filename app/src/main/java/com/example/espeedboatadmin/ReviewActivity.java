package com.example.espeedboatadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.espeedboatadmin.adapter.ReviewAdapter;
import com.example.espeedboatadmin.model.Review;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private ArrayList<Review> reviewArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        getData();

        recyclerView = findViewById(R.id.recycle_review);

        adapter = new ReviewAdapter(reviewArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReviewActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void getData() {
        reviewArrayList = new ArrayList<>();
        reviewArrayList.add(new Review("Mantap Gan!", "Devmptr", 5, 1, 2));
        reviewArrayList.add(new Review("Speedboatnya Kenceng banget!", "Ujang", 3, 3, 4));
        reviewArrayList.add(new Review("Spek Speedboatnya mantap, bisa buat main PUBG M", "David Gadgetin", 4, 2, 7));
        reviewArrayList.add(new Review("Speedboatnya bocor! saya terdampar", "Sobat Super", 1, 5, 17));
    }
}