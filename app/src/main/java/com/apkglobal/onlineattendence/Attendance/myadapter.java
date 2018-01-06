package com.apkglobal.onlineattendence.Attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apkglobal.onlineattendence.R;

import java.util.List;

/**
 * Created by Mayank on 8/2/2017.
 */

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    Context context;
    static int pos,c;


    private List<Names> mDataset;

    public void add(int position, Names item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Names item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public myadapter(List<Names> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public myadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendence_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        pos=vh.getNamePosition();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text.setText(mDataset.get(position).getName());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        RadioButton rb_1, rb_2;
        int position;



        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.tv_nam);
            rb_1 = (RadioButton) v.findViewById(R.id.radioButton1);
            rb_2 = (RadioButton) v.findViewById(R.id.radioButton2);
            rb_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position=getAdapterPosition();
                }
            });
        }

        public int getNamePosition()
        {
            return position;
        }
    }



    @Override
    public int getItemCount() {
        c=mDataset.size();
        return mDataset.size();
    }

    public void getRadioCount()
    {
        int l=getItemCount();
        for(int i=0;i<l;i++)
        {

        }
    }


}