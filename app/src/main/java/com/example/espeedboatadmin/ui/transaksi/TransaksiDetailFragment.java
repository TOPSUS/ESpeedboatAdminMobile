package com.example.espeedboatadmin.ui.transaksi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.adapter.ReviewAdapter;
import com.example.espeedboatadmin.adapter.TransaksiDetailAdapter;
import com.example.espeedboatadmin.client.RetrofitClient;
import com.example.espeedboatadmin.client.SessionManager;
import com.example.espeedboatadmin.model.Response;
import com.example.espeedboatadmin.model.Transaksi;
import com.example.espeedboatadmin.service.TransaksiService;
import com.example.espeedboatadmin.ui.review.ReviewDetailFragment;
import com.example.espeedboatadmin.ui.review.ReviewFragment;
import com.example.espeedboatadmin.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;

public class TransaksiDetailFragment extends Fragment {
    private TextView username, email, orderDate, person, status, from, fromDate, fromTime, to, toDate, toTime;
    private LinearLayout status_wrapper, btn_wrapper;
    private RecyclerView recyclerView;
    private View view;
    private RecyclerView.LayoutManager layoutManager;
    private TransaksiService service;
    private int transaksi_id = 0, isDone = 0;
    private Button btn_left, btn_right;
    private String bukti;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.transaksi_id = getArguments().getInt(Utils.TRANSAKSI_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transaksi_detail, container, false);;
        recyclerView = view.findViewById(R.id.recycle_ticket);
        service = RetrofitClient.getClient().create(TransaksiService.class);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        getData();

        return view;
    }

    private void getData() {
        Call<Response> getDetail = service.getTransaksiDetail(new SessionManager(view.getContext()).getAuthToken(), this.transaksi_id);

        getDetail.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        setIds();
                        setValue(response.body().getData().getTransaksi());
                        recyclerView.setAdapter(new TransaksiDetailAdapter(response.body().getData().getTransaksi().getDetailTransaksi()));
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void setIds() {
        this.username = (TextView) view.findViewById(R.id.td_username);
        this.email = (TextView) view.findViewById(R.id.td_email);
        this.orderDate = (TextView) view.findViewById(R.id.td_order_date);
        this.person = (TextView) view.findViewById(R.id.td_person);
        this.status = (TextView) view.findViewById(R.id.td_status);
        this.from = (TextView) view.findViewById(R.id.td_from);
        this.fromDate = (TextView) view.findViewById(R.id.td_from_date);
        this.fromTime = (TextView) view.findViewById(R.id.td_from_time);
        this.to = (TextView) view.findViewById(R.id.td_to);
        this.toDate = (TextView) view.findViewById(R.id.td_to_date);
        this.toTime = (TextView) view.findViewById(R.id.td_to_time);
        this.status_wrapper = (LinearLayout) view.findViewById(R.id.td_status_wrapper);
        this.btn_left = view.findViewById(R.id.td_btn_left);
        this.btn_right = view.findViewById(R.id.td_btn_right);
        this.btn_wrapper = view.findViewById(R.id.td_btn_wrapper);
    }

    private void setValue(Transaksi data) {
        this.username.setText(data.getUsername());
        this.email.setText(data.getEmail());
        this.orderDate.setText(data.getTanggal());
        this.person.setText(data.getPerson() + " Persons");
        this.from.setText(data.getAsal());
        this.fromDate.setText(data.getTanggalBerangkat());
        this.fromTime.setText(data.getJamBerangkat());
        this.to.setText(data.getTujuan());
        this.toDate.setText(data.getTanggalSampai());
        this.toTime.setText(data.getJamSampai());
        setStatus(data.getStatus());
        setButton(data.getStatus(), data.getBukti());
    }

    public void setStatus(String status) {
        String text = "Default";
        int color = R.drawable.status_default;
        if (status.equals("Terkonfirmasi")) {
            text = "Success";
            color = R.drawable.status_green;
        } else if (status.equals("Menunggu Konfirmasi")) {
            text = "Waiting";
            color = R.drawable.status_yellow;
        } else if (status.equals("Dibatalkan")) {
            text = "Canceled";
            color = R.drawable.status_red;
        } else if (status.equals("Expired")) {
            text = "Expired";
            color = R.drawable.status_orange;
        }
        this.status.setText(text);
        this.status_wrapper.setBackgroundResource(color);
    }

    private void setButton(String status, @Nullable String bukti) {
        if (bukti != null && status.equals("Menunggu Konfirmasi")) {
            this.btn_wrapper.setVisibility(View.VISIBLE);
            this.btn_left.setBackground(view.getContext().getDrawable(R.drawable.btn_green_style));
            this.btn_left.setText(R.string.btn_approve);
            this.isDone = 0;
            this.bukti = bukti;
            setButtonListener();
        } else if (bukti != null && status.equals("Terkonfirmasi")){
            this.btn_wrapper.setVisibility(View.VISIBLE);
            this.btn_left.setBackground(view.getContext().getDrawable(R.drawable.btn_blue_style));
            this.btn_left.setText(R.string.btn_review);
            this.isDone = 1;
            this.bukti = bukti;
            setButtonListener();
        } else {
            this.btn_wrapper.setVisibility(View.GONE);
            this.isDone = 0;
        }
    }

    private void setButtonListener() {
        this.btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDone == 0) {
                    // Approve
                    TransaksiService service = RetrofitClient.getClient().create(TransaksiService.class);
                    Call<Response> approveTransaksi = service.approveTransaksi(new SessionManager(view.getContext()).getAuthToken(), transaksi_id);

                    approveTransaksi.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getStatus() == 200) {
                                    Toast.makeText(getActivity(), response.body().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), response.body().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Pindah ke Fragment Review
                    ReviewFragment rFragment = new ReviewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Utils.TRANSAKSI_ID, transaksi_id);
                    rFragment.setArguments(bundle);

                    FragmentManager mFragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction mFragmentTransaction = mFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, rFragment, rFragment.getTag());
                    mFragmentTransaction.commit();
                }
            }
        });

        this.btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bukti != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bukti));
                    startActivity(browserIntent);
                }
            }
        });
    }
}