package com.example.myfirstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBAccess extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbStudent";
    static SQLiteDatabase dbStudent;

    public DBAccess(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbStudent = getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase dbStudent) {
        String sql;

        sql = "CREATE TABLE tblName (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "course TEXT, " +
                "year TEXT)";
        dbStudent.execSQL(sql);

        sql = "CREATE TABLE tblSchoolYear (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "schoolYearStart TEXT, " +
                "schoolYearEnd TEXT, " +
                "semester TEXT, " +
                "syID Integer)";
        dbStudent.execSQL(sql);

        sql = "CREATE TABLE tblGrades (" +
                "gradeId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "subjectCode TEXT, " +
                "subjectDescription TEXT, " +
                "gradeValue TEXT, " +
                "syID INTEGER)";
        dbStudent.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dbStudent.execSQL("DROP TABLE IF EXISTS tblName");
        dbStudent.execSQL("DROP TABLE IF EXISTS tblSchoolYear");
        dbStudent.execSQL("DROP TABLE IF EXISTS tblGrades");
    }
    public void StudentDeleteAll() {
        getReadableDatabase().delete("tblName", null, null);
    }
    public void SchoolYearDeleteAll() {
        getReadableDatabase().delete("tblSchoolYear", null, null);
    }
    public void GradeDeleteAll() {
        getReadableDatabase().delete("tblGrades", null, null);
    }
    public void StudentAdd(Student student_info) {

    }
    public void SchoolYearAdd(SchoolYear sy) {
        ContentValues values = new ContentValues();
        values.put("schoolYearStart", sy.SchoolYearStart);
        values.put("schoolYearEnd", sy.SchoolYearEnd);
        values.put("semester", sy.Semester);
        values.put("syId", sy.SchoolYearId);

        getWritableDatabase().insert("tblSchoolYear",null, values);
    }

    public List<SchoolYear> GetSchoolYears() {
        Cursor c = getReadableDatabase().rawQuery("select * from tblSchoolYear order by schoolYearStart, schoolYearEnd, semester, syId", null);
        c.moveToFirst();

        List<SchoolYear> school_year_list = new ArrayList<>();
        SchoolYear sy;

        while (!c.isAfterLast()) {
            sy = new SchoolYear();

            sy.SchoolYearStart = c.getString(1);
            sy.SchoolYearEnd = c.getString(2);
            sy.Semester = c.getString(3);
            sy.SchoolYearId = c.getString(4);
            school_year_list.add(sy);
            c.moveToNext();
        }
        c.close();
        return school_year_list;
    }
    public void GradeAdd(StudentGrade gr_list) {
        ContentValues values = new ContentValues();
        values.put("subjectCode", gr_list.SubjectCode);
        values.put("subjectDescription", gr_list.SubjectDescription);
        values.put("gradeValue", gr_list.GradeValue);
        values.put("syId", gr_list.SyId);

        getWritableDatabase().insert("tblGrades", null, values);
    }

    public List<StudentGrade> GetGrades(int syId) {
        Cursor c = getReadableDatabase().rawQuery("select gradeId, subjectCode, subjectDescription, gradeValue, syId from tblGrades " + "where syId= '" + syId + "'", null);
        c.moveToFirst();

        List<StudentGrade> grade_list = new ArrayList<>();
        StudentGrade gr_list;
        while (!c.isAfterLast()) {
            gr_list = new StudentGrade();
            gr_list.Id = c.getInt(0);
            gr_list.SubjectCode = c.getString(1);
            gr_list.SubjectDescription = c.getString(2);
            gr_list.GradeValue = c.getString(3);
            gr_list.SyId = c.getString(4);
            grade_list.add(gr_list);
            c.moveToNext();
        }
        c.close();
        return grade_list;
    }
    public String GetStudents() {
        Cursor c = getReadableDatabase().rawQuery("select id, firstName, lastName, course, year from tblName", null);
        String studentinfo = "";

        while (c.moveToFirst()) {
            Student studinfo = new Student();
            studinfo.StudentId = c.getString(0);

            studinfo.FirstName = c.getString(1);
            studinfo.LastName = c.getString(2);
            studinfo.Course = c.getString(3);
            studinfo.Year = c.getString(4);

            studentinfo += "Name: " + studinfo.FirstName + " " + studinfo.LastName + "\n" + "Course: " + studinfo.Course + "\n" + "Year: " + studinfo.Year;
        }
        c.close();
        return studentinfo;
    }
}




