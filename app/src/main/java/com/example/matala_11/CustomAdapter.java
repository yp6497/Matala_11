package com.example.matala_11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    String cityList[];
    Context context;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] cityList, int[] symbols) {
        this.context = context;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cityList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_lv_layout, null);
        TextView tvn = (TextView) view.findViewById(R.id.textView);
        tvn.setText("bo");
        return view;
    }

}
