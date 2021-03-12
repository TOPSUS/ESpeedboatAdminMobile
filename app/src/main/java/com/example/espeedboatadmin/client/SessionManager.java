package com.example.espeedboatadmin.client;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.espeedboatadmin.R;

public class SessionManager{
    private Context context;
    private String USER_TOKEN = "user_token";
    private SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
                                                                                Context.MODE_PRIVATE);

    public SessionManager (Context cont) {
        this.context = cont;
    }


    public void setAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        return "Bearer "+ sharedPreferences.getString(USER_TOKEN, null);
    }
}
