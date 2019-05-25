package com.example.rpp_lab3_v2.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rpp_lab3_v2.R;
import com.example.rpp_lab3_v2.StudentRecAdapter_v2;
import com.example.rpp_lab3_v2.dataBase.DB;
import com.example.rpp_lab3_v2.models.Student_v2;

import java.util.ArrayList;

public class ListActivity_v2  extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_student);
        DB dbHelper = new DB(getApplicationContext());
        ArrayList<Student_v2> students = dbHelper.readAll();
        recyclerView = findViewById(R.id.mRecycler);
        StudentRecAdapter_v2 studentsRecyclerAdapter = new StudentRecAdapter_v2(students);
        recyclerView.setAdapter(studentsRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
