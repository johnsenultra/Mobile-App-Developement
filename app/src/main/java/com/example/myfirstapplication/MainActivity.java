package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myfirstapplication.WebApi;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });

        Button btnGrades = findViewById(R.id.btnGrades);
        btnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Grades.class));
            }
        });

        Button btnSchedule = findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Schedule.class));
            }
        });

//        Student stud = new Student();
//        stud.StudentId = "09101";
//        stud.Course = "BSIT";
//        stud.FirstName = "John";
//        stud.LastName = "Doe";

        DBAccess dbAccess = new DBAccess(this);

        ProgressBar pb = new ProgressBar(this);

        WebApi api = new WebApi(this, "213020", "06092002", new ProgressBar(this));
        api.execute();
    }
}
