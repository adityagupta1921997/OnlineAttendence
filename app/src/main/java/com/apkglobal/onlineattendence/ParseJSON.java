package com.apkglobal.onlineattendence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mayank on 7/28/2017.
 */

public class ParseJSON {

    public static String[] names;
    public static String[] emails;
    public static String[] mobile;
    public static String[] classes;
    public static String[] attendance;
    public static final String JSON_URL="http://aptronnoida.com/Aditya_July4/addstudent/searchname.php?nameid=";
    public static final String JSON_ARRAY = "result";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_CLASS = "class";
    public static final String KEY_ATTENDANCE = "attendance";
    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            names = new String[users.length()];
            emails = new String[users.length()];
            mobile = new String[users.length()];
            classes= new String[users.length()];
            attendance=new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                names[i] = jo.getString(KEY_NAME);
                emails[i] = jo.getString(KEY_EMAIL);
                mobile[i] = jo.getString(KEY_MOBILE);
                classes[i] = jo.getString(KEY_CLASS);
                attendance[i]=jo.getString(KEY_ATTENDANCE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
