package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.matala_11.Grades.TABLE_GRADES;
import static com.example.matala_11.Users.TABLE_USERS;


/**
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12/1/2020
 * short description-
 */
public class ActivityFilteringSorting extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener  {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    ArrayList<String> tbl = new ArrayList<>();
    ArrayList<Integer> Kil = new ArrayList<Integer>();
    ListView lv;
    TextView tv,tv2;
    String[] names= {"a","b","c","d","e"};
    String[] subjects= {"aנ","bנ","cנ","dנ","eנ"};
    String[] reva= {"רבע ראשון","רבע ראשון","רבע ראשון","רבע ראשון","רבע ראשון"};
    String[] gardes= {"100","89","78","34","4"};
    String[] filter={"ציונים לפי:","תלמיד","מקצוע","רבע"};
    String[] sort={"ציונים באופן:","ברירת מחדל","עולה","יורד"};
    Spinner SFilter, SSort,SNames;
    ArrayAdapter adp;
    EditText Esubject;
    String [] revaim= {"רבע ראשון","רבע שני","רבע שלישי","רבע רביעי"};
    int posSf,posSs,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_sorting);


        SFilter = findViewById(R.id.SFilter);
        SSort = findViewById(R.id.SSort);
        SNames = findViewById(R.id.SNames);
        lv = findViewById(R.id.lv);
        Esubject = findViewById(R.id.Esubject);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        SFilter.setOnItemSelectedListener(this);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, filter);
        SFilter.setAdapter(adp);

        SSort.setOnItemSelectedListener(this);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sort);
        SSort.setAdapter(adp);
    }


    /**
     * param @view
     * description- creates menu.
     */
    public void approval(View view) {

        if(posSf==2){
            count++;

        }
    }


    /**
     * description- creates menu.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.SFilter) {

            posSf=position;

            if (position == 0) {

                SNames.setAdapter(null);
                Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();
            }
            //else {
             //   CustomAdapter customadp = new CustomAdapter(getApplicationContext(),
               //     names, subjects, reva, gardes);
               // lv.setAdapter(customadp);
                /**
                 * if student chosen- gives a spinner with the all names to choose one.
                 */
            if (position == 1) {

                    db = hlp.getWritableDatabase();
                    tbl = new ArrayList<>();
                    tbl.add("בחר תלמיד");
                    crsr = db.query(TABLE_USERS, null, null, null, null, null, null);

                    int col2 = crsr.getColumnIndex(Users.NAME);
                    int col10 = crsr.getColumnIndex(Users.ACTIVE);
                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        String name = crsr.getString(col2);
                        int activ = crsr.getInt(col10);
                        if (activ == 0) {
                            String tmp = " " + name;
                            tbl.add(tmp);
                        }
                        crsr.moveToNext();
                    }
                    crsr.close();
                    db.close();

                    SNames.setOnItemSelectedListener(this);
                    adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
                    SNames.setAdapter(adp);
                }

                /**
                 *
                 */
                else if (position == 2) {

                    Esubject.setText("הכנס מקצוע");
                    SNames.setAdapter(null);
                }

                /**
                 *
                 */
                else if (position == 3) {

                    SNames.setOnItemSelectedListener(this);
                    adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, revaim);
                    SNames.setAdapter(adp);
                }
            }
        else if(spinner.getId() == R.id.SSort){

            posSs=position;

            if (position == 0)  Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();
            //else{
              //  CustomAdapter customadp = new CustomAdapter(getApplicationContext(),
              //          names, subjects, reva, gardes);
              //  lv.setAdapter(customadp);
            //}
        }
    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        if (st.endsWith("נתוני התלמידים")) {
            Intent si = new Intent(this, ActivityDataDisplay.class);
            startActivity(si);
        }
        else if (st.endsWith("קליטת נתונים")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.endsWith("קרדיטים")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("קליטת ציונים")) {
            Intent si = new Intent(this, ActivityGradeInput.class);
            startActivity(si);
        }
        return true;
    }
}