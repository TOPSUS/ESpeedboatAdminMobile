package com.example.espeedboatadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button review_btn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user_token", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);
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