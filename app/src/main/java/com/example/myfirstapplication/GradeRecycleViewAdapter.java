package com.example.myfirstapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeRecycleViewAdapter extends RecyclerView.Adapter <GradeRecycleViewHolder> {
    public List<StudentGrade> grade_list;

    @NonNull
    @Override
    public GradeRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grades_row, parent, false);
        return new GradeRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeRecycleViewHolder holder, int position) {

        StudentGrade grade = grade_list.get(position);

        holder.code.setText(grade.SubjectCode);
        holder.des.setText(grade.SubjectDescription);
        holder.val.setText(grade.GradeValue);
    }

    @Override
    public int getItemCount() {
        if (grade_list == null) {
            return 0;
        }
        return grade_list.size();
    }
}
