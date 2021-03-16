package com.example.espeedboatadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.espeedboatadmin.client.RetrofitClient;
import com.example.espeedboatadmin.client.SessionManager;
import com.example.espeedboatadmin.model.Auth;
import com.example.espeedboatadmin.model.Response;
import com.example.espeedboatadmin.service.LoginService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginAdmin extends AppCompatActivity {
    private TextInputLayout layoutemail, layoutpass;
    private TextInputEditText txtEmail, txtPassword;
    private Button submitlogin;
    View view;
    Context context;
    private TextView lupapass;
    SessionManager sesi;
    private LoginService servicelogin;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        init();
    }

    private void init() {
        servicelogin = RetrofitClient.getClient().create(LoginService.class);
        layoutemail = findViewById(R.id.EmailLayout);
        layoutpass = findViewById(R.id.PaswordLayout);
        txtEmail = findViewById(R.id.emailadmin);
        txtPassword = findViewById(R.id.passwordadmin);
        submitlogin = findViewById(R.id.loginbutton);
        dialog = new ProgressDialog(LoginAdmin.this);
        dialog.setCancelable(false);

        submitlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    login();
                }
            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!txtEmail.getText().toString().isEmpty()){
                    layoutemail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtPassword.getText().toString().length()>1){
                    layoutpass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void login() {
        dialog.setMessage("Logging In");
        dialog.show();
        Call<Response> login = servicelogin.postLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
        login.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().getStatus() == 200) {
                    Auth auth = response.body().getData().getAuth();
                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                    sesi = new SessionManager(getApplicationContext());
                    sesi.setAuthToken(String.valueOf(auth.getToken()));
                    sesi.setUserNama(String.valueOf(auth.getUser().getNama()));
                    sesi.setUserEmail(String.valueOf(auth.getUser().getEmail()));
                    sesi.setFlag(true);
                    startActivity(new Intent(LoginAdmin.this, DashboardAdminActivity.class));
                    finish();
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Email/Password Salah!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private boolean validate() {
        if (txtEmail.getText().toString().isEmpty()){
            layoutemail.setErrorEnabled(true);
            layoutemail.setError("Email is Required");
            return false;
        }

        if(txtPassword.getText().toString().length()<5){
            layoutpass.setErrorEnabled(true);
            layoutpass.setError("Required at least 8 characters");
            return false;
        }
        return true;
    }
}