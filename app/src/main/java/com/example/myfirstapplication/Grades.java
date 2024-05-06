package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private TextView StudInfo;
    List<SchoolYear> semester;
    RecyclerView rvGrades;
    List<StudentGrade> studentGradeList;
    DBAccess da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        da = new DBAccess(this);

        String Stud_Info = da.GetStudents();
        StudInfo = findViewById(R.id.fname);
        StudInfo.setText(Stud_Info);

        da = new DBAccess(this);
        semester = new ArrayList<>();
        semester = PopSchoolYear();

        studentGradeList = new ArrayList<>();
        studentGradeList = GetListofGrades();
        SchoolYearSpinnerAdapter spinnerAdapter = new SchoolYearSpinnerAdapter(this,R.layout.school_year_row,semester);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner spinner = findViewById(R.id.spinner);
        TextView txtGradesLabel = findViewById(R.id.txtGradesLabel);
        spinner.setAdapter(spinnerAdapter);

        rvGrades = findViewById(R.id.rvGrades);

        GradeRecycleViewAdapter gr = new GradeRecycleViewAdapter();

        RecyclerView.LayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setItemPrefetchEnabled(true);
        rvGrades.setLayoutManager(l);
        rvGrades.setItemAnimator(new DefaultItemAnimator());
        rvGrades.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        gr.grade_list = studentGradeList;
        rvGrades.setAdapter(gr);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SchoolYear selectedItem = semester.get(position);
//                txtGradesLabel.setText("Grades of " + selectedItem.Semester + " School Year: " + selectedItem.SchoolYearStart + " - " + selectedItem.SchoolYearEnd);
                studentGradeList.clear();
                gr.grade_list = da.GetGrades(Integer.parseInt(selectedItem.SchoolYearId));

                gr.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private  List<SchoolYear> PopSchoolYear() {

        return da.GetSchoolYears();
    }
    private List<StudentGrade> GetListofGrades() {
        List<StudentGrade> list = new ArrayList<>();

        StudentGrade studentGrade;

        studentGrade = new StudentGrade();
        studentGrade.Id = 1;
        studentGrade.SubjectCode = "IT 101";
        studentGrade.SubjectDescription = "Database Management";
        studentGrade.GradeValue = "2.00";
        studentGrade.SyId = "1";

        list.add(studentGrade);

        studentGrade = new StudentGrade();
        studentGrade.Id = 2;
        studentGrade.SubjectCode = "IT 102";
        studentGrade.SubjectDescription = "System Analysis";
        studentGrade.GradeValue = "2.50";
        studentGrade.SyId = "2";

        list.add(studentGrade);

        return list;
    }
}