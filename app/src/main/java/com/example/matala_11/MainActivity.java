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
import android.widget.TextView;
import android.widget.Toast;


/**
 * The type Main activity.
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12 /1/2021
 * short description- Entering student data.
 */
public class MainActivity extends AppCompatActivity {

    EditText nameE, addressE, studentPE, homePE, parentName1E, parentName2E, parentP1E, parentP2E;
    String name, address, studentP,homeP, parentName1, parentName2, parentP1, parentP2;
    TextView tv;

    SQLiteDatabase db;
    HelperDB hlp;

    Switch sw;
    int s; //active or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = findViewById(R.id.sw);
        tv = findViewById(R.id.tv);
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

        s=1;
    }

    /**
     * description- kolet all the fields
     * @param view the view
     */
    public void aprrov(View view) {


        if (parentP1E == null || parentName1E == null || addressE == null || nameE == null || studentPE == null)
            Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();

        else {

            name = nameE.getText().toString();
            studentP = studentPE.getText().toString();
            parentName1 = parentName1E.getText().toString();
            parentName2 = parentName2E.getText().toString();
            parentP1 = parentP1E.getText().toString();
            parentP2 = parentP2E.getText().toString();
            address = addressE.getText().toString();
            homeP = homePE.getText().toString();

            ContentValues cv = new ContentValues();
            cv.put(Users.NAME, name);
            cv.put(Users.STUDENT_PHONE, studentP);
            cv.put(Users.PARENT_NAME_1, parentName1);
            cv.put(Users.PARENT_NAME_2, parentName2);
            cv.put(Users.PARENT_PHONE_1, parentP1);
            cv.put(Users.PARENT_PHONE_2, parentP2);
            cv.put(Users.ADDRESS, address);
            cv.put(Users.HOME_PHONE, homeP);
            cv.put(Users.ACTIVE, s);

            db = hlp.getWritableDatabase();
            db.insert(Users.TABLE_USERS, null, cv);
            db.close();

            nameE.setText("");
            studentPE.setText("");
            parentName1E.setText("");
            parentName2E.setText("");
            parentP1E.setText("");
            parentP2E.setText("");
            addressE.setText("");
            homePE.setText("");
        }
    }

    /**
     * description- if switch is on= active, else= not active and puts the value in s.
     * @param view the view
     */
    public void active_or_not(View view) {

        if (sw.isChecked()) {
            s = 0;
            tv.setText("פעיל");
        }
        else {
            s = 1;
            tv.setText("לא פעיל");
        }
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
        if (st.endsWith("קרדיטים")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        if (st.endsWith("קליטת ציונים")) {
            Intent si = new Intent(this, ActivityGradeInput.class);
            startActivity(si);
        }
        else if (st.endsWith("נתוני הציונים")) {
            Intent si = new Intent(this, ActivityFilteringSorting.class);
            startActivity(si);
        }
        else if (st.endsWith("נתוני התלמידים")) {
            Intent si = new Intent(this, ActivityDataDisplay.class);
            startActivity(si);
        }
        return true;
    }
}
