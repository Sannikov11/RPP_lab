package com.example.rpp_lab3_v2.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rpp_lab3_v2.R;
import com.example.rpp_lab3_v2.StudentRecAdapter_v1;
import com.example.rpp_lab3_v2.dataBase.DB;
import com.example.rpp_lab3_v2.models.Student_v1;

import java.util.ArrayList;

public class ListActivity_v1 extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_student);
        DB db = new DB(getApplicationContext());
        ArrayList<Student_v1> studentV1s = db.readAll_v1();
        recyclerView = findViewById(R.id.mRecycler);
        StudentRecAdapter_v1 studentsRecAdapter = new StudentRecAdapter_v1(studentV1s);
        recyclerView.setAdapter(studentsRecAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
