package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityFilteringSorting extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ListView lv;
    TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_sorting);

        lv=findViewById(R.id.lv);

        lv.setOnItemClickListener(this);

        /*
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
        lv.setAdapter(adp);

        CustomAdapter customadp = new CustomAdapter(getApplicationContext(),
               tv,tv2);
         lv.setAdapter(customadp);
        */
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}