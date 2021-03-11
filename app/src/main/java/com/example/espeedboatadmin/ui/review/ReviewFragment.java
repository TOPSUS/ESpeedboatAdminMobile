package com.example.espeedboatadmin.ui.review;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.adapter.ReviewAdapter;
import com.example.espeedboatadmin.model.Review;

import java.util.ArrayList;

public class ReviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private ArrayList<Review> reviewArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        recyclerView = view.findViewById(R.id.recycle_review);

        getData();

        adapter = new ReviewAdapter(reviewArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void getData() {
        reviewArrayList = new ArrayList<>();
        reviewArrayList.add(new Review("Mantap Gan!", "Devmptr", 5, 1, 2));
        reviewArrayList.add(new Review("Speedboatnya Kenceng banget!", "Ujang", 3, 3, 4));
        reviewArrayList.add(new Review("Spek Speedboatnya mantap, bisa buat main PUBG M", "David Gadgetin", 4, 2, 7));
        reviewArrayList.add(new Review("Speedboatnya bocor! saya terdampar", "Sobat Super", 1, 5, 17));
        reviewArrayList.add(new Review("Mantap Gan!", "Devmptr", 5, 1, 2));
        reviewArrayList.add(new Review("Speedboatnya Kenceng banget!", "Ujang", 3, 3, 4));
        reviewArrayList.add(new Review("Spek Speedboatnya mantap, bisa buat main PUBG M", "David Gadgetin", 4, 2, 7));
        reviewArrayList.add(new Review("Speedboatnya bocor! saya terdampar", "Sobat Super", 1, 5, 17));
    }
}