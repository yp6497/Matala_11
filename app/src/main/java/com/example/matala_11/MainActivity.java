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
import android.widget.Switch;


/**
 * The type Main activity.
 *
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12 /1/2021 short description-
 */
public class MainActivity extends AppCompatActivity {


    EditText nameE, addressE, studentPE, homePE, parentName1E, parentName2E, parentP1E, parentP2E;
    String name, address, studentP,homeP, parentName1, parentName2, parentP1, parentP2;

    SQLiteDatabase db;
    HelperDB hlp;

    Switch sw;
    int s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = findViewById(R.id.sw);
        nameE= findViewById(R.id.name);
        studentPE= findViewById(R.id.studentP);
        parentName1E= findViewById(R.id.parentName1);
        parentName2E= findViewById(R.id.parentName2);
        parentP1E= findViewById(R.id.parentP1);
        parentP2E= findViewById(R.id.parentP2);
        addressE= findViewById(R.id.address);
        homePE= findViewById(R.id.homeP);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

    }

    /**
     * description-
     * @param view the view
     */
    public void aprrov(View view) {

        name= nameE.getText().toString();
        studentP= studentPE.getText().toString();
        parentName1= parentName1E.getText().toString();
        parentName2= parentName2E.getText().toString();
        parentP1= parentP1E.getText().toString();
        parentP2= parentP2E.getText().toString();
        address= addressE.getText().toString();
        homeP= homePE.getText().toString();

    }


    /**
     * description- if switch is on= active, else= not active and puts the value in s.
     * @param view the view
     */
    public void active_or_not(View view) {

        if (sw.isChecked()) s = 0;
        else s = 1;
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
        if (st.endsWith("Grades")) {
            Intent si = new Intent(this, ActivityGradeInput.class);
            startActivity(si);
        }
        return true;
    }
}



