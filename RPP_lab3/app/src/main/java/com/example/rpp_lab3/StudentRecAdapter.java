package com.example.rpp_lab3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpp_lab3.models.Student;

import java.util.ArrayList;

public class StudentRecAdapter extends RecyclerView.Adapter<StudentRecAdapter.ViewHolder> {

    private ArrayList<Student> students;

    public StudentRecAdapter(ArrayList<Student> students) {
        super();
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder studentViewHolder = (ViewHolder) viewHolder;
        studentViewHolder.FIO.setText(students.get(i).getId() + ") " + students.get(i).getFIO());
        studentViewHolder.date.setText(students.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView FIO;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FIO = itemView.findViewById(R.id.FIO);
            date = itemView.findViewById(R.id.date);
        }
    }
}



