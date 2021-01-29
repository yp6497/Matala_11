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
import android.widget.EditText;

/**
 * The type Activity grade input.
 */
public class ActivityGradeInput extends AppCompatActivity {


    SQLiteDatabase db;
    HelperDB hlp;

    String subject, grade, name;
    EditText edSubject, /**
     * Which subject the grade related to.
     */
    edGrade, /**
     * The grade.
     */
    edName; /**
     * The student's name.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);

        edSubject= findViewById(R.id.edSubject);
        edGrade= findViewById(R.id.edGrade);
        edName= findViewById(R.id.edName);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

    }



    /**
     *
     * @param view the view
     */
    public void approval(View view) {
      /*
        ContentValues cv= new ContentValues();
        cv.put(MainActivity.Grades.SUBJECT, subject);
        cv.put(MainActivity.Grades.GRADE, grade);

        db = hlp.getWritableDatabase();
        db.insert(MainActivity.Grades.TABLE_GRADES, null, cv);
        db.close();


       */
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

