package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityFilteringSorting extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ListView lv;
    TextView tv,tv2;
    String[] names= {"a","b","c","d","e"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_sorting);

        lv=findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //ArrayAdapter<String> adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
         //       names);
        //lv.setAdapter(adp);

        CustomAdapter customadp = new CustomAdapter(getApplicationContext(),
               names);
         lv.setAdapter(customadp);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }






    /**
     * description- creates menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * description- if "Main screen" selected: return back to the Main activity.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("Students information")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("Filtering and sorting")) {
            Intent si = new Intent(this, ActivityFilteringSorting.class);
            startActivity(si);
        }
        return true;
    }
}