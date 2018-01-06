package com.apkglobal.onlineattendence.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mayank on 7/28/2017.
 */

public class ViewDetails extends Fragment implements View.OnClickListener{


    private Button btn_getdata;
    private EditText et_search;
    private TextView textViewResult;

    private ListView list_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_details,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_getdata=(Button)getActivity().findViewById(R.id.btn_getdata);
        //list_view=(ListView)getActivity().findViewById(R.id.list_view);
        et_search=(EditText)getActivity().findViewById(R.id.et_search);
        textViewResult=(TextView)getActivity().findViewById(R.id.textViewResult);
        btn_getdata.setOnClickListener(this);


    }

    private void sendRequest() {
        String search_name=et_search.getText().toString().trim();
        if(search_name.equals(""))
        {
            Toast.makeText(getActivity(), "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        String url = ParseJSON.JSON_URL+et_search.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        String name="";
        String email="";
        String mobile = "";
        String ca="";
        String attendance="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(ParseJSON.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            name = collegeData.getString(ParseJSON.KEY_NAME);
            email = collegeData.getString(ParseJSON.KEY_EMAIL);
            mobile= collegeData.getString(ParseJSON.KEY_MOBILE);
            ca=collegeData.getString(ParseJSON.KEY_CLASS);
            attendance=collegeData.getString(ParseJSON.KEY_ATTENDANCE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText("Name:\t"+name+"\nEmail:\t" +email+ "\nMobile:\t"+ mobile+ "\nClass:\t"+ ca+ "\nAttendance:\t"+ attendance);
    }

        /*ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();*/

        /*CustomList cl = new CustomList(getActivity(), ParseJSON.names, ParseJSON.emails, ParseJSON.mobile, ParseJSON.classes);
        list_view.setAdapter(cl);*/
    @Override
    public void onClick(View view) {
        sendRequest();
    }
}
