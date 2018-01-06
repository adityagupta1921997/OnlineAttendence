package com.apkglobal.onlineattendence.Attendance;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apkglobal.onlineattendence.CustomList;
import com.apkglobal.onlineattendence.ParseJSON;
import com.apkglobal.onlineattendence.R;

import java.util.ArrayList;
import java.util.List;


public class AttendenceActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String JSON_URL = "http://aptronnoida.com/Aditya_July4/addstudent/fetchsname.php";

    private Button btn_getdata,btn_sa;
    TextView text1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<Names> nam,attend;
    final Context context = this;
    String clas;
    Handler pdCanceller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        Intent intent=getIntent();
        clas=intent.getStringExtra("CLAS");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        //recycler view does not depend on the size of the content of adapter.
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        btn_getdata=(Button)findViewById(R.id.btn_getdata);
        btn_sa=(Button)findViewById(R.id.btn_sa);
        btn_getdata.setOnClickListener(this);
        btn_sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                text1 = (TextView) dialog.findViewById(R.id.text1);
                text1.setText("Do you want to save this attendance!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.save);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonCANCEL);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });
                dialogButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.btn_getdata:
                getData();
                break;
        }

    }

    private void getData(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);
        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                loading.cancel();
            }
        };
        pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    private void showJSON(String json){
        ParseJSON2 pj = new ParseJSON2(json,clas);
        pj.parseJSON();
        nam = pj.getNames();
        attend=pj.getAttendance();

        adapter = new myadapter(nam);
        recyclerView.setAdapter(adapter);
    }

}
