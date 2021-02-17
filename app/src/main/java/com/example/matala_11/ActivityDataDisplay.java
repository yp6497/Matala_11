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
    Spinner Snames,ActiveOrNot;
    EditText Hphone, Address, Pphone1, ParentN1,Pphone2,ParentN2, SName,SPhone,activeOrNot;

    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;
    ArrayList<Integer> Kil = new ArrayList<Integer>();

    int[]kil;
    String[] activeArr={"פעיל","לא פעיל"};
    String[] notactiveArr={"פעיל","לא פעיל"};
    String oldName,id="1";
    String SNAME,SPHONE,ADDRESS, HOMEP, PARENTN1,PARENTN2,PPHONE1,PPHONE2,ACTIVEORNOT;
    int pos,a;//active or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        ActiveOrNot=findViewById(R.id.ActiveOrNot);
        SName=findViewById(R.id.SName);
        SPhone=findViewById(R.id.SPhone);
        ParentN1=findViewById(R.id.parentN1);
        ParentN2=findViewById(R.id.parentN2);
        Pphone1=findViewById(R.id.Pphone1);
        Pphone2=findViewById(R.id.Pphone2);
        Address=findViewById(R.id.Address);
        Hphone=findViewById(R.id.HPhone);
        Snames= findViewById(R.id.Snames);

       // oldName=SName.getText().toString();

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

        Spinner spinner = (Spinner) parent;


        if (spinner.getId() == R.id.Snames) {
            pos = position;
            if (position == 0) {

                SName.setText(""); //לעשות מזה פעולה?
                Hphone.setText("");
                Address.setText("");
                Pphone1.setText("");
                ParentN1.setText("");
                Pphone2.setText("");
                ParentN2.setText("");
                SPhone.setText("");
            } else {
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
                        ActiveOrNot.setOnItemSelectedListener(this);
                        if(active==0) {
                            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, activeArr);
                            ActiveOrNot.setAdapter(adp);
                        }
                        else {
                            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, notactiveArr);
                            ActiveOrNot.setAdapter(adp);
                        }
                    }
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
            }
        }
        else{
            if(position==0) a=0;
            else a=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * description- Updates the data
     */
    public void update(View view) {

        SNAME=SName.getText().toString();
        SPHONE = SPhone.getText().toString();
        ADDRESS= Address.getText().toString();
        HOMEP = Hphone.getText().toString();
        PARENTN1= ParentN1.getText().toString();
        PARENTN2= ParentN2.getText().toString();
        PPHONE1= Pphone1.getText().toString();
        PPHONE2= Pphone2.getText().toString();

        ContentValues cv = new ContentValues();
        db=hlp.getWritableDatabase();
        cv.put(Users.NAME, SNAME);
        cv.put(Users.STUDENT_PHONE, SPHONE);
        cv.put(Users.PARENT_NAME_1, PARENTN1);
        cv.put(Users.PARENT_NAME_2, PARENTN2);
        cv.put(Users.PARENT_PHONE_1, PPHONE1);
        cv.put(Users.PARENT_PHONE_2, PPHONE2);
        cv.put(Users.ADDRESS, ADDRESS);
        cv.put(Users.HOME_PHONE, HOMEP);
        cv.put(Users.ACTIVE, a);
        db.update(TABLE_USERS, cv, "_id = ?", new String[]{String.valueOf(pos)}); //אחרי שמעדכנים את פעיל לא פעיל- לשנות את הספינר עוד הפעם
        db.close();

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        tbl.add("בחר תלמיד");
        crsr = db.query(TABLE_USERS, null, null,null, null, null,null);

        int col2 = crsr.getColumnIndex(Users.NAME);
        int col10 = crsr.getColumnIndex(Users.ACTIVE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
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