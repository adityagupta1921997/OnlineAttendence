package com.apkglobal.onlineattendence.Attendance;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank on 8/1/2017.
 */

public class ParseJSON2 {
    public static String[] names;
    public static String[] classes;
    public static String[] attendance;
    public static final String JSON_ARRAY = "data";
    private JSONArray n = null;


    List<Names> N,A ;


    private String json;
    private String cs;
    private static int j=0;

    public ParseJSON2(String json,String cs){

        this.json = json;
        this.cs=cs;
    }
    /*public ParseJSON2(String cs,int j)
    {
        this.cs=cs;
        this.j=j;
    }*/

    protected void parseJSON(){
        JSONObject jsonObject=null;

        try {
            jsonObject = new JSONObject(json);
            n = jsonObject.getJSONArray(JSON_ARRAY);


            names = new String[n.length()];
            classes = new String[n.length()];
            attendance=new String[n.length()];
            N = new ArrayList<>();
            A=new ArrayList<>();



            for(int i=0;i< n.length();i++){
                Names n_object =  new Names();
                Names a_object= new Names();

                JSONObject jo = n.getJSONObject(i);

                names[i] = jo.getString("name");
                classes[i]=jo.getString("class");

                if(classes[i].equals(cs))
                {
                    n_object.setName(names[i]);
                    a_object.setAttendance(attendance[i]);
                    N.add(n_object);
                    A.add(a_object);

                }





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*public static int noOfItems()
    {
        return j;
    }*/
    List<Names> getNames()
    {
        //function to return the final populated list
        return N;
    }
    List<Names> getAttendance()
    {
        return A;
    }


}
