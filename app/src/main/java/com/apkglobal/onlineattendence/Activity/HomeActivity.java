package com.apkglobal.onlineattendence.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkglobal.onlineattendence.R;

public class HomeActivity extends AppCompatActivity {

    TextView tv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_home=(TextView)findViewById(R.id.tv_home);
    }
}
