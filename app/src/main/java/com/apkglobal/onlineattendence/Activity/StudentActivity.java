package com.apkglobal.onlineattendence.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.apkglobal.onlineattendence.R;
import com.apkglobal.onlineattendence.SharedPreference.Helper;
import com.apkglobal.onlineattendence.SharedPreference.Login2Activity;
import com.apkglobal.onlineattendence.SharedPreference.Login3Activity;
import com.apkglobal.onlineattendence.SharedPreference.TeacherActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StudentActivity extends AppCompatActivity {

    EditText et_fromdate,et_todate;
    Button btn_report;
    Toolbar toolbar;
    Helper h;
    Calendar myCalendar1 = Calendar.getInstance();
    Calendar myCalendar2 = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        et_fromdate=(EditText)findViewById(R.id.et_fromdate);
        et_todate=(EditText)findViewById(R.id.et_todate);
        btn_report=(Button)findViewById(R.id.btn_report);
        h=new Helper(this);
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        et_fromdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(StudentActivity.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_todate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(StudentActivity.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        inittoolbar();
        h.checkLogin();

    }

    private void inittoolbar() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.logout_menu);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_fromdate.setText(sdf.format(myCalendar1.getTime()));
        et_todate.setText(sdf.format(myCalendar2.getTime()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout)
        {
            new Helper(this).logout();
            startLogin();
            Toast.makeText(getApplicationContext(),"Logout...",Toast.LENGTH_SHORT).show();

        }
        return true;
    }




    private void startLogin() {
        Intent toLogin=new Intent(StudentActivity.this,Login3Activity.class);
        startActivity(toLogin);
        finish();
    }
}
