package com.apkglobal.onlineattendence.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkglobal.onlineattendence.R;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    TextView tv_cont,tv_name1,tv_name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tv_cont=(TextView)findViewById(R.id.tv_cont);
        tv_name1=(TextView)findViewById(R.id.tv_name1);
        tv_name2=(TextView)findViewById(R.id.tv_name2);
    }
}
