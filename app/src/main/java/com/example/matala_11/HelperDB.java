package com.example.matala_11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.matala_11.Grades.GRADE;
import static com.example.matala_11.Grades.NAMEID;
import static com.example.matala_11.Grades.REVA;
import static com.example.matala_11.Grades.SUBJECT;
import static com.example.matala_11.Grades.TABLE_GRADES;
import static com.example.matala_11.Users.ACTIVE;
import static com.example.matala_11.Users.ADDRESS;
import static com.example.matala_11.Users.HOME_PHONE;
import static com.example.matala_11.Users.KEY_ID;
import static com.example.matala_11.Users.NAME;
import static com.example.matala_11.Users.PARENT_NAME_1;
import static com.example.matala_11.Users.PARENT_NAME_2;
import static com.example.matala_11.Users.PARENT_PHONE_1;
import static com.example.matala_11.Users.PARENT_PHONE_2;
import static com.example.matala_11.Users.STUDENT_PHONE;
import static com.example.matala_11.Users.TABLE_USERS;

public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_USERS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+STUDENT_PHONE+" TEXT,";
        strCreate+=" "+PARENT_NAME_1+" TEXT,";
        strCreate+=" "+PARENT_NAME_2+" TEXT,";
        strCreate+=" "+PARENT_PHONE_1+" TEXT,";
        strCreate+=" "+PARENT_PHONE_2+" TEXT,";
        strCreate+=" "+ADDRESS+" TEXT,";
        strCreate+=" "+HOME_PHONE+" TEXT,";
        strCreate+=" "+ACTIVE+" INTEGER ";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_GRADES;
        //strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" ("+NAMEID+" INTEGER,";
        strCreate+=" "+SUBJECT+" TEXT,";
        strCreate+=" "+REVA+" TEXT,";
        strCreate+=" "+GRADE+" INTEGER";
        strCreate+=");";
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

