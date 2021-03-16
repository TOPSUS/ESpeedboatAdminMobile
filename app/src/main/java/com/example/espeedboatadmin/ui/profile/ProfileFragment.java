package com.example.espeedboatadmin.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.espeedboatadmin.DashboardAdminActivity;
import com.example.espeedboatadmin.LoginAdmin;
import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.client.RetrofitClient;
import com.example.espeedboatadmin.client.SessionManager;
import com.example.espeedboatadmin.model.ResponseAuth;
import com.example.espeedboatadmin.model.ResponseLogout;
import com.example.espeedboatadmin.service.LoginService;
import com.example.espeedboatadmin.service.LogoutService;
import com.example.espeedboatadmin.service.ReviewService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    Button logout;
    View view;
    LogoutService servicelogout;
    String token;
    SessionManager sesi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = view.findViewById(R.id.btnLogout);
        servicelogout = RetrofitClient.getClient().create(LogoutService.class);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun_logout();
            }
        });
        return view;
    }

    private void fun_logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Log Out");
        builder.setMessage("Apakah anda yakin ingin Log Out?");
        builder.setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Call<ResponseLogout> logout = servicelogout.postLogout(new SessionManager(view.getContext()).getToken());
                logout.enqueue(new Callback<ResponseLogout>() {
                    @Override
                    public void onResponse(Call<ResponseLogout> call, Response<ResponseLogout> response) {
                        sesi = new SessionManager(view.getContext());
                        sesi.setFlagOff();
                        startActivity(new Intent(view.getContext(), LoginAdmin.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseLogout> call, Throwable t) {
                        Toast.makeText(getContext(), "Log Out Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}