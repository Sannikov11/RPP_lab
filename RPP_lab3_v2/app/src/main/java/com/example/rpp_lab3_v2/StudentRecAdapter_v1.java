package com.example.rpp_lab3_v2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpp_lab3_v2.models.Student_v1;

import java.util.ArrayList;

public class StudentRecAdapter_v1 extends RecyclerView.Adapter<StudentRecAdapter_v1.ViewHolder> {


    private ArrayList<Student_v1> studentV1s;

    public StudentRecAdapter_v1(ArrayList<Student_v1> studentV1s) {
        super();
        this.studentV1s = studentV1s;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView FIO, date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FIO = itemView.findViewById(R.id.FIO);
            date = itemView.findViewById(R.id.date);
        }
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
        studentViewHolder.FIO.setText(studentV1s.get(i).getFIO());
        studentViewHolder.date.setText(studentV1s.get(i).getDate());
    }


    @Override
    public int getItemCount() {
        return studentV1s.size();
    }
}



