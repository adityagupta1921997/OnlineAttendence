package com.apkglobal.onlineattendence.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apkglobal.onlineattendence.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Pawan on 07-07-2017.
 */

public class AddStudent extends Fragment {
    EditText et_sname,et_semail,et_smobile,et_sclass,et_attendance;
    Button btn_submitstudent;
    String sname,semail,smobile,sclass,sattendance;
    int attendance;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addstudent, container, false);
        et_sname=(EditText)view.findViewById(R.id.et_sname);
        et_semail=(EditText)view.findViewById(R.id.et_semail);
        et_smobile=(EditText)view.findViewById(R.id.et_smobile);
        et_sclass=(EditText)view.findViewById(R.id.et_sclass);
        et_attendance=(EditText)view.findViewById(R.id.et_attendance);
        btn_submitstudent=(Button)view.findViewById(R.id.btn_submitstudent);
        btn_submitstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname=et_sname.getText().toString();
                semail=et_semail.getText().toString();
                smobile=et_smobile.getText().toString();
                sclass=et_sclass.getText().toString();
                sattendance=et_attendance.getText().toString();

                Send send=new Send();
                send.execute(sname,semail,smobile,sclass,sattendance);
            }
        });
        return view;
    }

    private class Send extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait....");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            sname=strings[0];
            semail=strings[1];
            smobile=strings[2];
            sclass=strings[3];
            sattendance=strings[4];

           // attendance=Integer.parseInt(sattendance);


            try {
                URL url=new URL("http://aptronnoida.com/Aditya_July4/addstudent/inserttostudent.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String data= URLEncoder.encode("nameid","utf-8")+"="+URLEncoder.encode(sname,"utf-8")+"&&"+URLEncoder.encode("emailid","utf-8")+"="+URLEncoder.encode(semail,"utf-8")+"&&"+URLEncoder.encode("mobileid","utf-8")+"="+URLEncoder.encode(smobile,"utf-8")+"&&"+URLEncoder.encode("classid","utf-8")+"="+URLEncoder.encode(sclass,"utf-8")+"&&"+URLEncoder.encode("attendanceid","utf-8")+"="+URLEncoder.encode(sattendance,"utf-8");



                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            return "Students details submitted";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            et_sname.setText("");
            et_semail.setText("");
            et_smobile.setText("");
            et_sclass.setText("");
            et_attendance.setText("");
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
