package com.example.espeedboatadmin.client;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.espeedboatadmin.R;

public class SessionManager{
    private Context context;
    private String USER_TOKEN = "user_token";
    private String USER_EMAIL = "user_email";
    private String USER_NAMA = "user_nama";
    private String USER_FLAG = "user_flag";
    private SharedPreferences sharedPreferences;

    public SessionManager (Context cont) {
        this.context = cont;
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void setAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        return "Bearer "+ sharedPreferences.getString(USER_TOKEN, null);
    }

    public void setUserNama(String nama) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAMA, nama);
        editor.apply();
    }

    public String getUserNama() {
        return sharedPreferences.getString(USER_NAMA, null);
    }

    public void setUserEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(USER_EMAIL, null);
    }

    public void setFlag(Boolean flag) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(USER_FLAG, true);
        editor.apply();
    }

    public boolean getUserFlag() {
        return sharedPreferences.getBoolean(USER_FLAG, false);
    }

    public void setFlagOff() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(USER_TOKEN, null);
    }
}
