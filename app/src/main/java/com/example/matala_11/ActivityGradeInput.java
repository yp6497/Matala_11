package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The type Activity grade input.
 */
public class ActivityGradeInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    SQLiteDatabase db;
    HelperDB hlp;
    Spinner Sr;

    String subject, grade, name, reva;
    EditText edSubject, /**
     * Which subject the grade related to.
     */
    edGrade, /**
     * The grade.
     */
    edName; /**
     * The student's name.
     */

    String [] revaim= {"רבע ראשון","רבע שני","רבע שלישי","רבע רביעי"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);

        edSubject= findViewById(R.id.edSubject);
        edGrade= findViewById(R.id.edGrade);
        edName= findViewById(R.id.edName);
        Sr= findViewById(R.id.spinner);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        Sr.setOnItemSelectedListener(this);
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, revaim);
        Sr.setAdapter(adp);

    }

    /**
     *
     * @param view the view
     */
    public void approval(View view) {

        if(edName==null || edSubject==null || edGrade==null){
            Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();
        }
        else {

        ContentValues cv= new ContentValues();
        cv.put(Grades.SUBJECT, subject);
        cv.put(Grades.GRADE, grade);
        cv.put(Grades.GRADE, reva);

        db = hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES, null, cv);
        db.close();

        }
    }

    /**
     * description- Which _ is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){
            reva= "רבע ראשון";
        }
        else if(position==1){
            reva= "רבע שני";
        }
        else if(position==2){
            reva= "רבע שלישי";
        }
        else if(position==3){
            reva= "רבע רביעי";
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
     * description- if "Main screen" selected: return back to the Main activity.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        if (st.endsWith("Main screen")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        return true;
    }
}

