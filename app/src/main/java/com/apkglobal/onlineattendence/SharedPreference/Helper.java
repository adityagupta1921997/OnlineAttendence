package com.apkglobal.onlineattendence.SharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.apkglobal.onlineattendence.login.LoginActivity;

/**
 * Created by Mayank on 7/30/2017.
 */

public class Helper {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Helper(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password) {
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();

    }

    public boolean checkLogin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return  isEmailEmpty || isPasswordEmpty;
    }


    public void logout(){
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }
}
