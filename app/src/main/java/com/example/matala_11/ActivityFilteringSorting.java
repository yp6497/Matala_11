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
    String[] subjects= {"aנ","bנ","cנ","dנ","eנ"};
    String[] reva= {"רבע ראשון","רבע ראשון","רבע ראשון","רבע ראשון","רבע ראשון"};
    String[] gardes= {"100","89","78","34","4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_sorting);

        lv=findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        CustomAdapter customadp = new CustomAdapter(getApplicationContext(),
                names,subjects,reva,gardes);
         lv.setAdapter(customadp);
    }

    /**
     * description- creates menu.
     */
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
     * description- the menu. Moves to the selected activity.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();
        if (st.endsWith("information")) {
            Intent si = new Intent(this, ActivityDataDisplay.class);
            startActivity(si);
        }
        else if (st.endsWith("Students information")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("Grades")) {
            Intent si = new Intent(this, ActivityGradeInput.class);
            startActivity(si);
        }
        return true;
    }
}