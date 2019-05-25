package com.example.rpp_lab3_v2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpp_lab3_v2.models.Student_v2;

import java.util.ArrayList;

public class StudentRecAdapter_v2 extends RecyclerView.Adapter<StudentRecAdapter_v2.ViewHolder>  {

    private ArrayList<Student_v2> student_v2s;
    public StudentRecAdapter_v2(ArrayList<Student_v2> student_v2s) {
        super();
        this.student_v2s =  student_v2s;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView surname, name, patronymic, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            surname = itemView.findViewById(R.id.surname);
            name = itemView.findViewById(R.id.name);
            patronymic = itemView.findViewById(R.id.patronymic);
            date = itemView.findViewById(R.id.date);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student_v2, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder studentViewHolder = (ViewHolder) viewHolder;
        studentViewHolder.surname.setText(student_v2s.get(i).getSurname());
        studentViewHolder.name.setText(student_v2s.get(i).getName());
        studentViewHolder.patronymic.setText(student_v2s.get(i).getPatronymic());
        studentViewHolder.date.setText(student_v2s.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return student_v2s.size();
    }


}
