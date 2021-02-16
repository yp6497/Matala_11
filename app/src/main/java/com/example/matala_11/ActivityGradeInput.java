package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.matala_11.Users.KEY_ID;
import static com.example.matala_11.Users.TABLE_USERS;


/**
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12/1/2020
 * short description-
 */
public class ActivityGradeInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    //ArrayList<String> subjects = new ArrayList<>();
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;
    ArrayList<Integer> Kil = new ArrayList<Integer>();
    Spinner Sr,Sname;
    String subject, grade, reva;
    EditText edSubject, /**
     * Which subject the grade related to.
     */
    edGrade; /**
     * The grade.
     */
    int count=0,kid,pos;
    String[] namess=null;
    String [] revaim= {"רבע ראשון","רבע שני","רבע שלישי","רבע רביעי"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);

        edSubject = findViewById(R.id.edSubject);
        edGrade = findViewById(R.id.edGrade);
        Sr = findViewById(R.id.Sr);
        Sname = findViewById(R.id.Sname);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        //Sname.setOnItemSelectedListener(this);
        //tbl.add("בחר תלמיד");
        subject = edSubject.getText().toString();
        grade = edGrade.getText().toString();

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        tbl.add("בחר תלמיד");
        crsr = db.query(TABLE_USERS, null, null, null, null, null, null);

        //int col1 = crsr.getColumnIndex(Users.KEY_ID);
        int col2 = crsr.getColumnIndex(Users.NAME);

        /*
        int col3 = crsr.getColumnIndex(Users.STUDENT_PHONE);
        int col4 = crsr.getColumnIndex(Users.PARENT_NAME_1);
        int col5 = crsr.getColumnIndex(Users.PARENT_NAME_2);
        int col6 = crsr.getColumnIndex(Users.PARENT_PHONE_1);
        int col7 = crsr.getColumnIndex(Users.PARENT_PHONE_2);
        int col8 = crsr.getColumnIndex(Users.ADDRESS);
        int col9 = crsr.getColumnIndex(Users.HOME_PHONE);
         */
        int col10 = crsr.getColumnIndex(Users.ACTIVE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            //int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            int activ = crsr.getInt(col10);
            if (activ == 0) {
                String tmp = " " + name;
                tbl.add(tmp);
                //Kil.add(key);
            }
            //count++;
             crsr.moveToNext();
        }
        crsr.close();
        db.close();

            //namess=new String[count];
            //=tbl;
            Sname.setOnItemSelectedListener(this);
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
            Sname.setAdapter(adp);

            Sr.setOnItemSelectedListener(this);
            ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, revaim);
            Sr.setAdapter(adp);

    }

    /**
     * description-
     * @param view the view
     */
    public void approval(View view) {

        //Sname.setOnItemSelectedListener(this);
        //adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
        //Sname.setAdapter(adp);

        Sr.setOnItemSelectedListener(this);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, revaim);
        Sr.setAdapter(adp);

        if(edSubject==null || edGrade==null || pos==0){

            Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();
        }
        else {

            ContentValues cv= new ContentValues();
            //cv.put(Grades.NAME, name);
            cv.put(Grades.SUBJECT, subject);
            cv.put(Grades.REVA, reva);
            cv.put(Grades.GRADE, grade);

            db = hlp.getWritableDatabase();
            db.insert(Grades.TABLE_GRADES, null, cv);
            db.close();

            edSubject.setText("");
            edGrade.setText("");
        }
    }

    /**
     * description- Which reva is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.Sname){

            pos=position;

            //=position;
            //name = namess[position];
        }

        else if (spinner.getId() == R.id.Sr) {
            if (position == 0) {
                reva = "רבע ראשון";
            } else if (position == 1) {
                reva = "רבע שני";
            } else if (position == 2) {
                reva = "רבע שלישי";
            } else if (position == 3) {
                reva = "רבע רביעי";
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        if (st.endsWith("קרדיטים")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("נתוני התלמידים")) {
            Intent si = new Intent(this, ActivityDataDisplay.class);
            startActivity(si);
        }
        else if (st.endsWith("קליטת נתונים")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("נתוני הציונים")) {
            Intent si = new Intent(this, ActivityFilteringSorting.class);
            startActivity(si);
        }
        return true;
    }


}

