package com.example.matala_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * The type Activity grade input.
 */
public class ActivityGradeInput extends AppCompatActivity {


    String subject;
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

    }

    /**
     *
     * @param view the view
     */
    public void approval(View view) {

        ContentValues cv= new ContentValues();
        cv.put(MainActivity.Grades.SUBJECT, subject);
    }
}