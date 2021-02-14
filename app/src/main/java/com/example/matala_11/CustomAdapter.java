package com.example.matala_11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String namelist[];
    String subjectlist[];
    String revalist[];
    String gardelist[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] namelist, String[] subjectlist, String[] revalist, String[] gardelist) {
        this.context = context;
        this.namelist = namelist;
        this.subjectlist= subjectlist;
        this. revalist= revalist;
        this.gardelist=gardelist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {

        return namelist.length;
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
        TextView name = (TextView) view.findViewById(R.id.textView4);
        TextView grade = (TextView) view.findViewById(R.id.textView);
        TextView subject = (TextView) view.findViewById(R.id.textView3);
        TextView reva = (TextView) view.findViewById(R.id.textView2);
        name.setText(namelist[i]);
        grade.setText(gardelist[i]);
        reva.setText(revalist[i]);
        subject.setText(subjectlist[i]);

        return view;
    }
}
