package com.example.espeedboatadmin.ui.transaksi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.adapter.ReviewAdapter;
import com.example.espeedboatadmin.adapter.TransaksiAdapter;
import com.example.espeedboatadmin.client.RetrofitClient;
import com.example.espeedboatadmin.client.SessionManager;
import com.example.espeedboatadmin.model.Response;
import com.example.espeedboatadmin.model.Transaksi;
import com.example.espeedboatadmin.service.ReviewService;
import com.example.espeedboatadmin.service.TransaksiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListTransaksiFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private RecyclerView.LayoutManager layoutManager;
    private TransaksiService service;
    private String type;

    public ListTransaksiFragment(String type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_transaksi, container, false);
        recyclerView = view.findViewById(R.id.recycle_transaksi);
        service = RetrofitClient.getClient().create(TransaksiService.class);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        getData();

        return view;
    }

    private void getData() {
        Call<Response> getTransaksi;
        if (type == "done") {
            getTransaksi = service.getTransaksiSelesai(new SessionManager().getAuthToken());
        } else {
            getTransaksi = service.getTransaksiProses(new SessionManager().getAuthToken());
        }

        getTransaksi.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        List<Transaksi> transaksiList = response.body().getData().getTransaksi();
                        recyclerView.setAdapter(new TransaksiAdapter(transaksiList));
                        Toast.makeText(getActivity(), response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}