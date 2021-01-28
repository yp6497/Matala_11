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
import android.widget.EditText;


import static com.example.matala_11.MainActivity.Grades.GRADE;
import static com.example.matala_11.MainActivity.Grades.SUBJECT;
import static com.example.matala_11.MainActivity.Grades.TABLE_GRADES;
import static com.example.matala_11.MainActivity.Users.ADDRESS;
import static com.example.matala_11.MainActivity.Users.AVEIBALE;
import static com.example.matala_11.MainActivity.Users.HOME_PHONE;
import static com.example.matala_11.MainActivity.Users.KEY_ID;
import static com.example.matala_11.MainActivity.Users.NAME;
import static com.example.matala_11.MainActivity.Users.PARENT_NAME_1;
import static com.example.matala_11.MainActivity.Users.PARENT_NAME_2;
import static com.example.matala_11.MainActivity.Users.PARENT_PHONE_1;
import static com.example.matala_11.MainActivity.Users.PARENT_PHONE_2;
import static com.example.matala_11.MainActivity.Users.STUDENT_PHONE;
import static com.example.matala_11.MainActivity.Users.TABLE_USERS;

/**
 * The type Main activity.
 *
 * @author Yulia Piavka <yp6497@bs.amalnet.k12.il>
 * @version 1.1
 * @since 12/1/2021 short description-
 */
public class MainActivity extends AppCompatActivity {

    EditText name, address, studentP, homeP, parentName1, parentName2, parentP1, parentP2;
    String Sname, Saddress, SstudentP, ShomeP, SparentName1, SparentName2, SparentP1, SparentP2;
    SQLiteDatabase db;
    HelperDB hlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

    }

    /**
     * The type Users.
     */
    public class Users {

        public static final String TABLE_USERS = "Users";
        public static final String KEY_ID = "_id";
        public static final String NAME="Name";
        public static final String AVEIBALE = "aveible";
        public static final String ADDRESS = "address";
        public static final String STUDENT_PHONE = "StudentP";
        public static final String HOME_PHONE = "HomeP";
        public static final String PARENT_NAME_1 = "ParentN1";
        public static final String PARENT_NAME_2 = "ParentN2";
        public static final String PARENT_PHONE_1 = "ParentP1";
        public static final String PARENT_PHONE_2 = "ParentP2";
    }

    public class Grades {

        public static final String TABLE_GRADES = "Grades";
        public static final String SUBJECT = "Subject";
        public static final String GRADE = "Grade";
    }

    public class HelperDB extends SQLiteOpenHelper {

        String strCreate, strDelete;

        private static final String DATABASE_NAME = "dbexam.db";
        private static final int DATABASE_VERSION = 1;


        public HelperDB(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            strCreate="CREATE TABLE "+TABLE_USERS;
            strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
            strCreate+=" "+NAME+" TEXT,";
            strCreate += " "+ AVEIBALE+" INTEGER,";
            strCreate += " "+ADDRESS+" TEXT,";
            strCreate += " "+ STUDENT_PHONE+" TEXT,";
            strCreate += " "+ HOME_PHONE+" TEXT,";
            strCreate += " "+ PARENT_NAME_1+" TEXT,";
            strCreate += " "+ PARENT_NAME_2+" TEXT,";
            strCreate += " "+ PARENT_PHONE_1+" TEXT,";
            strCreate += " "+ PARENT_PHONE_2+" TEXT,";
            strCreate+=");";
            db.execSQL(strCreate);

            strCreate="CREATE TABLE "+ TABLE_GRADES;
            strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
            strCreate+=" "+SUBJECT+" TEXT,";
            strCreate += " "+ GRADE+" INTEGER,";
            db.execSQL(strCreate);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            strDelete = "DROP TABLE IF EXISTS " + TABLE_USERS;
            db.execSQL(strDelete);
            strDelete = "DROP TABLE IF EXISTS " + TABLE_GRADES;
            db.execSQL(strDelete);

            onCreate(db);
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
