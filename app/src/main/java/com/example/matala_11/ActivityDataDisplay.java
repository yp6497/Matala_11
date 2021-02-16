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
import android.widget.Switch;

import java.util.ArrayList;

import static com.example.matala_11.Users.KEY_ID;
import static com.example.matala_11.Users.TABLE_USERS;


/**
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12/1/2020
 * short description-
 */
public class ActivityDataDisplay extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    Spinner Snames;
    EditText Hphone, Address, Pphone1, ParentN1,Pphone2,ParentN2, SName,SPhone,activeOrNot;

    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;
    ArrayList<Integer> Kil = new ArrayList<Integer>();

    int[]kil;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        activeOrNot=findViewById(R.id.activeOrNot);
        SName=findViewById(R.id.SName);
        SPhone=findViewById(R.id.SPhone);
        ParentN1=findViewById(R.id.parentN1);
        ParentN2=findViewById(R.id.parentN2);
        Pphone1=findViewById(R.id.Pphone1);
        Pphone2=findViewById(R.id.Pphone2);
        Address=findViewById(R.id.Address);
        Hphone=findViewById(R.id.HPhone);
        Snames= findViewById(R.id.Snames);

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        tbl.add("בחר תלמיד");
        crsr = db.query(TABLE_USERS, null, null,null, null, null,null);

        /**
         * prints names
         */
        int col1 = crsr.getColumnIndex(KEY_ID);
        int col2 = crsr.getColumnIndex(Users.NAME);
        int col10 = crsr.getColumnIndex(Users.ACTIVE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            int activ= crsr.getInt(col10);
            if(activ==1) {
                String tmp = " " + name + " -לא פעיל";
                tbl.add(tmp);
            }
            else {
                String tmp = " " + name;
                tbl.add(tmp);
            }
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

        pos=position;
        if(position==0) {

            SName.setText("");
            Hphone.setText("");
            Address.setText("");
            Pphone1.setText("");
            ParentN1.setText("");
            Pphone2.setText("");
            ParentN2.setText("");
            SPhone.setText("");
        }

        else{
            int idS = position;
            db = hlp.getWritableDatabase();
            //tbl = new ArrayList<>();
            crsr = db.query(TABLE_USERS, null, null, null, null, null, null);

            int col1 = crsr.getColumnIndex(KEY_ID);
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
                if (idS == key) {
                    SName.setText(name);
                    SPhone.setText(sdudentP);
                    ParentN1.setText(parentN1);
                    Pphone1.setText(parentP1);
                    ParentN2.setText(parentN2);
                    Pphone2.setText(parentP2);
                    Address.setText(address);
                    Hphone.setText(homeP);
                    if (active == 0) activeOrNot.setText("פעיל");
                    else activeOrNot.setText("לא פעיל");
                }
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * description- Updates the data
     */
    public void update(View view) {

        //db.delete(TABLE_USERS, KEY_ID+"=?", new String[]{Integer.toString()};
        //db.delete(TABLE_USERS, KEY_ID+"=?",new String[] {pos});



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

        if (st.endsWith("קליטת נתונים")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("קרדיטים")) {
            Intent si = new Intent(this, creditsActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("קליטת ציונים")) {
            Intent si = new Intent(this, ActivityGradeInput.class);
            startActivity(si);
        }
        else if (st.endsWith("נתוני הציונים")) {
            Intent si = new Intent(this, ActivityFilteringSorting.class);
            startActivity(si);
        }
        return true;
    }


}