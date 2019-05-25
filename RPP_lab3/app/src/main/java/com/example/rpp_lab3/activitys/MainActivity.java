package com.example.rpp_lab3.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rpp_lab3.R;
import com.example.rpp_lab3.dataBase.DB;

public class MainActivity extends AppCompatActivity {

    Button btn_show;
    Button btn_add;
    Button btn_change;
    private boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB db = new DB(getApplicationContext());
        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        if (!created) {
            db.addFiveStudents();
            created = true;
        }
        btn_show = findViewById(R.id.btn_show);
        btn_add = findViewById(R.id.btn_add);
        btn_change = findViewById(R.id.btn_change);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListActivity();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db = new DB(getApplicationContext());
                db.addStudent();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db = new DB(getApplicationContext());
                db.changeStudent();
            }
        });

    }
    @Override
    protected void onStart() {
        Log.i("onStart", "MainActivity");
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("onRestoreInstanceState", "MainActivity");
        super.onRestoreInstanceState(savedInstanceState);
        created = savedInstanceState.getBoolean("created");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("onSaveInstanceState", "MainActivity");
        super.onSaveInstanceState(outState);
        outState.putBoolean("created", created);
    }

    public void startListActivity(){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
