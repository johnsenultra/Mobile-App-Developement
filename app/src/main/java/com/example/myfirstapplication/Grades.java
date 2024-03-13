package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Grades extends AppCompatActivity {
    List<SchoolYear> semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        semester = new ArrayList<>();
        semester = PopSchoolYear();
//        semester.add("2022-2023 1st Semester");
//        semester.add("2022-2023 2nd Semester");
//        semester.add("2023-2024 1st Semester");
//        semester.add("2023-2024 2nd Semester");

//        ArrayAdapter arr = new ArrayAdapter(this, android.R.layout.simple_spinner_item,semester);
//        arr.setDropDownViewResource(android.R.layout.simple_spinner_item);

        SchoolYearSpinnerAdapter spinnerAdapter = new SchoolYearSpinnerAdapter(this,R.layout.school_year_row,semester);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner spinner = findViewById(R.id.spinner);
        TextView txtGradesLabel = findViewById(R.id.txtGradesLabel);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolYear selectedItem = semester.get(position);
                txtGradesLabel.setText("Grades of " + selectedItem.Semester + " School Year: " + selectedItem.SchoolYearStart + " - " + selectedItem.SchoolYearEnd);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private  List<SchoolYear> PopSchoolYear() {
        List<SchoolYear> schoolYearList = new ArrayList<>();

        SchoolYear schoolYear;

        schoolYear = new SchoolYear();
        schoolYear.SchoolYearStart = "2022";
        schoolYear.SchoolYearEnd = "2023";
        schoolYear.Semester = "1st Semester";

        schoolYearList.add(schoolYear);

        schoolYear = new SchoolYear();
        schoolYear.SchoolYearStart = "2022";
        schoolYear.SchoolYearEnd = "2024";
        schoolYear.Semester = "2nd Semester";

        schoolYearList.add(schoolYear);

        schoolYear = new SchoolYear();
        schoolYear.SchoolYearStart = "2023";
        schoolYear.SchoolYearEnd = "2024";
        schoolYear.Semester = "1st Semester";

        schoolYearList.add(schoolYear);

        schoolYear = new SchoolYear();
        schoolYear.SchoolYearStart = "2023";
        schoolYear.SchoolYearEnd = "2024";
        schoolYear.Semester = " 2nd Semester";

        schoolYearList.add(schoolYear);

        return schoolYearList;
    }
}