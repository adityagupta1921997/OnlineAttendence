package com.apkglobal.onlineattendence;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.apkglobal.onlineattendence.Activity.MainActivity;
import com.apkglobal.onlineattendence.Activity.NavigationActivity;
import com.apkglobal.onlineattendence.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //to hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //to hide the action bar
        /*getSupportActionBar().hide();*/
        int time = 2000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);
    }
}
