package com.apkglobal.onlineattendence;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mayank on 7/28/2017.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] names;
    private String[] emails;
    private String[] mobile;
    private String[] classes;
    private Activity context;

    public CustomList(Activity context, String[] names, String[] emails, String[] mobile, String [] classes) {
        super(context, R.layout.fetch_list, names);
        this.context = context;
        this.names = names;
        this.emails = emails;
        this.mobile = mobile;
        this.classes=classes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.fetch_list, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.tv_name);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_email);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.tv_mobile);
        TextView textViewIdea = (TextView) listViewItem.findViewById(R.id.tv_class);
        textViewId.setText(names[position]);
        textViewName.setText(emails[position]);
        textViewEmail.setText(mobile[position]);
        textViewIdea.setText(classes[position]);
        return listViewItem;
    }
}

