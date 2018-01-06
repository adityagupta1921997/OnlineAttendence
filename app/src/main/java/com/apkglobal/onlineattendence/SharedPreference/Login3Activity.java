package com.apkglobal.onlineattendence.SharedPreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apkglobal.onlineattendence.Activity.StudentActivity;
import com.apkglobal.onlineattendence.R;

public class Login3Activity extends AppCompatActivity implements View.OnClickListener {
    EditText et_email,et_password;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        et_email= (EditText) findViewById(R.id.et_email);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        if(email.equals("adityagupta")&&password.equals("123456789")) {


            new Helper(this).saveLoginDetails(email, password);
            Intent i = new Intent(Login3Activity.this, StudentActivity.class);
            startActivity(i);
            finish();

        }
        else
        {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }


        et_email.setText("");
        et_password.setText("");


        /*if (!new Helper(this).checkLogin()){
            Intent toMain=new Intent(Login2Activity.this,TeacherActivity.class);
            startActivity(toMain);
            Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"Login Failed...",Toast.LENGTH_SHORT).show();

        }*/
    }
}

