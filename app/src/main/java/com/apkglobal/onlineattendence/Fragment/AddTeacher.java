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

public class AddTeacher extends Fragment {

    EditText et_name,et_email,et_mobile,et_qualifications;
    Button btn_submitteacher;
    String tname,temail,tmobile,tqualifications;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addteacher, container, false);
        et_name=(EditText)view.findViewById(R.id.et_name);
        et_email=(EditText)view.findViewById(R.id.et_email);
        et_mobile=(EditText)view.findViewById(R.id.et_mobile);
        et_qualifications=(EditText)view.findViewById(R.id.et_qualifications);
        btn_submitteacher=(Button)view.findViewById(R.id.btn_submitteacher);
        btn_submitteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tname=et_name.getText().toString();
                temail=et_email.getText().toString();
                tmobile=et_mobile.getText().toString();
                tqualifications=et_qualifications.getText().toString();

                Send send=new Send();
                send.execute(tname,temail,tmobile,tqualifications);
            }
        });
        return view;
    }

    private class Send extends AsyncTask<String ,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            tname=strings[0];
            temail=strings[1];
            tmobile=strings[2];
            tqualifications=strings[3];

            try {
                URL url=new URL("http://aptronnoida.com/Aditya_July4/addteacher/insertteacher.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String data= URLEncoder.encode("nameid","utf-8")+"="+URLEncoder.encode(tname,"utf-8")+"&&"+URLEncoder.encode("emailid","utf-8")+"="+URLEncoder.encode(temail,"utf-8")+"&&"+URLEncoder.encode("mobileid","utf-8")+"="+URLEncoder.encode(tmobile,"utf-8")+"&&"+URLEncoder.encode("qualificationsid","utf-8")+"="+URLEncoder.encode(tqualifications,"utf-8");



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

            return "Teacher details submitted";
        }

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            et_name.setText("");
            et_email.setText("");
            et_mobile.setText("");
            et_qualifications.setText("");
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
