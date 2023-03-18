package com.example.mess_locator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Users> {
    private Context mContext;
    private int mResource;
    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Users> objects) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(mContext).inflate(mResource,parent,false);
        TextView mname = view.findViewById(R.id.textView);
        TextView maddress = view.findViewById(R.id.textView2);

        mname.setText("Mess Name:"+getItem(position).getMessName());
        maddress.setText("Mess Address"+getItem(position).getaddress());
        return view;
    }
}
