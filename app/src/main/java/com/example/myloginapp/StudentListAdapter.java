package com.example.myloginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends ArrayAdapter<StudentData> {

    private Context mContext;
    int mResource;
    public StudentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StudentData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position ,View contextView, ViewGroup parent) {

        String name = getItem(position).getName();
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();

        StudentData student = new StudentData(name,date,time);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View mItemView = inflater.inflate(mResource,parent,false);

        TextView tvName = (TextView) mItemView.findViewById(R.id.textView3);
        TextView tvDate = (TextView) mItemView.findViewById(R.id.textView4);
        TextView tvTime = (TextView) mItemView.findViewById(R.id.textView5);

        tvName.setText(name);
        tvDate.setText(date);
        tvTime.setText(time);

        return mItemView;
    }
}
