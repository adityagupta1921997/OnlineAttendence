package com.apkglobal.onlineattendence.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
 * Created by Mayank on 7/30/2017.
 */

public class AddClass extends Fragment implements AdapterView.OnItemSelectedListener {
    @Nullable
    EditText et_class;
    TextView tv_addclass;
    String[] teachers = { "A", "B", "C", "D", "E",  };
    String aclass,ateacher;
    ProgressDialog progressDialog;
    Button btn_submit;
    Spinner spinner;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_class,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et_class=(EditText)getActivity().findViewById(R.id.et_class);
        tv_addclass=(TextView)getActivity().findViewById(R.id.tv_addclass);
        Spinner spin = (Spinner)getActivity().findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,teachers);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        btn_submit=(Button)getActivity().findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aclass=et_class.getText().toString();
                Send send=new Send();
                send.execute(aclass,ateacher);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(),teachers[i] , Toast.LENGTH_LONG).show();
        ateacher=teachers[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class Send extends AsyncTask<String ,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            aclass=strings[0];
            ateacher=strings[1];

            try {
                URL url=new URL("http://aptronnoida.com/Aditya_July4/addclass/insertclass.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String data= URLEncoder.encode("classid","utf-8")+"="+URLEncoder.encode(aclass,"utf-8")+"&&"+URLEncoder.encode("teacherid","utf-8")+"="+URLEncoder.encode(ateacher,"utf-8");



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

            return "Class details submitted";
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
            et_class.setText("");
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
