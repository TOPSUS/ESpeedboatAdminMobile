package com.example.espeedboatadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.espeedboatadmin.client.SessionManager;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private Button review_btn;
    private Intent intent;
    boolean isLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoggedIn = new SessionManager(getApplicationContext()).getUserFlag();
                if(isLoggedIn){
                    startActivity(new Intent(MainActivity.this, DashboardAdminActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(MainActivity.this, LoginAdmin.class));
                    finish();
                }
            }
        }, 1500);
    }

}