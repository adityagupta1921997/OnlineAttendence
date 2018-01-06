package com.apkglobal.onlineattendence.SharedPreference;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.apkglobal.onlineattendence.Attendance.AttendenceActivity;
import com.apkglobal.onlineattendence.Attendance.ParseJSON2;
import com.apkglobal.onlineattendence.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TeacherActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_submitattend;
    EditText et_date;
    Toolbar toolbar;
    String[] cl = {"B.tech", "BCA", "MCA", "M.tech"};
    Helper h;
    Calendar myCalendar = Calendar.getInstance();
    Shared shared;
    Spinner spincl;
    String temp;
    ParseJSON2 parseJSON2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=new Shared(getApplicationContext());
        shared.withoutlogin();
        setContentView(R.layout.activity_teacher);
        spincl = (Spinner)findViewById(R.id.spinner2);
        spincl.setOnItemSelectedListener(this);
        ArrayAdapter ca = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cl);
        ca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spincl.setAdapter(ca);
        btn_submitattend=(Button)findViewById(R.id.btn_submitattend);
        et_date=(EditText)findViewById(R.id.et_date);
        h=new Helper(this);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        et_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TeacherActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_submitattend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent attend=new Intent(TeacherActivity.this,AttendenceActivity.class);
                attend.putExtra("CLAS",temp);
                startActivity(attend);
                finish();

            }
        });
        inittoolbar();
        h.checkLogin();

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void inittoolbar() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.logout_menu);

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
        Intent toLogin=new Intent(TeacherActivity.this,Login2Activity.class);
        startActivity(toLogin);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String clas=cl[i];
        temp(clas);

    }

    private void temp(String clas) {
         temp=clas;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
