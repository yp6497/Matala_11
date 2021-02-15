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
import android.widget.Spinner;

import java.util.ArrayList;

import static com.example.matala_11.Users.TABLE_USERS;

public class ActivityDataDisplay extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    Spinner Snames;
    EditText Hphone, Address, Pphone1, parentN1,Pphone2,parentN2, SName,SPhone;

    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;
    ArrayList<Integer> Kil = new ArrayList<Integer>();

    int[]kil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        SName=findViewById(R.id.SName);
        SPhone=findViewById(R.id.SPhone);
        parentN1=findViewById(R.id.parentN1);
        parentN2=findViewById(R.id.parentN2);
        Pphone1=findViewById(R.id.Pphone1);
        Pphone2=findViewById(R.id.Pphone2);
        Address=findViewById(R.id.Address);
        Hphone=findViewById(R.id.HPhone);
        Snames= findViewById(R.id.Snames);

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        tbl.add("בחר תלמיד");
        crsr = db.query(TABLE_USERS, null, null,null, null, null,null);

        int col1 = crsr.getColumnIndex(Users.KEY_ID);
        int col2 = crsr.getColumnIndex(Users.NAME);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            String tmp = " " + name;
            tbl.add(tmp);
            Kil.add(key);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

        //kil=Kil;
        Snames.setOnItemSelectedListener(this);
        adp= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
        Snames.setAdapter(adp);

    }

    /**
     * description- Which reva is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        db = hlp.getWritableDatabase();
       // tbl = new ArrayList<>();
        crsr = db.query(TABLE_USERS, null, null,null, null, null,null);

        int col1 = crsr.getColumnIndex(Users.KEY_ID);
        int col2 = crsr.getColumnIndex(Users.NAME);
        int col3 = crsr.getColumnIndex(Users.STUDENT_PHONE);
        int col4 = crsr.getColumnIndex(Users.PARENT_NAME_1);
        int col5 = crsr.getColumnIndex(Users.PARENT_NAME_2);
        int col6 = crsr.getColumnIndex(Users.PARENT_PHONE_1);
        int col7 = crsr.getColumnIndex(Users.PARENT_PHONE_2);
        int col8 = crsr.getColumnIndex(Users.ADDRESS);
        int col9 = crsr.getColumnIndex(Users.HOME_PHONE);
        int col10 = crsr.getColumnIndex(Users.ACTIVE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            String sdudentP = crsr.getString(col3);
            String parentN1 = crsr.getString(col4);
            String parentN2 = crsr.getString(col5);
            String parentP1 = crsr.getString(col6);
            String parentP2 = crsr.getString(col7);
            String address = crsr.getString(col8);
            String homeP = crsr.getString(col9);
            int active = crsr.getInt(col10);

            //String tmp = " " + key + ", " + name + ", " + sdudentP + ", " + parentN1 + ", " + parentN2 + ", " + parentP1 + ", " + parentP2 + ", " + address + ", " + homeP + ", " + active;
            //String tmp = " " + name
            //SName.setText(Kil(key));
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

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
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("Grades")) {
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