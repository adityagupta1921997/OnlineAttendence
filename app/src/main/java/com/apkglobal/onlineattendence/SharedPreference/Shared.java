package com.apkglobal.onlineattendence.SharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Mayank on 8/5/2017.
 */

public class Shared {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    int mode = 0;
    String filename = "meri";
    String login = "login";

    public Shared(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(filename, mode);
        ed = sp.edit();
    }

    public void withoutlogin() {
        if (!this.islogin())
        {
            Intent i=new Intent(context,Login2Activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
    }

    public void withlogin()
    {
        ed.putBoolean(login,true);
        ed.commit();
    }

    private boolean islogin() {
        return sp.getBoolean(login,false);
    }
}
