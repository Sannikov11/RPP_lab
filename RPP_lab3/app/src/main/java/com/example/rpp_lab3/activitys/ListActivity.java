package com.example.rpp_lab3.activitys;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rpp_lab3.R;
import com.example.rpp_lab3.StudentRecAdapter;
import com.example.rpp_lab3.dataBase.DB;
import com.example.rpp_lab3.models.Student;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_student);
        DB db = new DB(getApplicationContext());
        ArrayList<Student> students = db.readAll();
        recyclerView = findViewById(R.id.mRecycler);
        StudentRecAdapter studentsRecAdapter = new StudentRecAdapter(students);
        recyclerView.setAdapter(studentsRecAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
