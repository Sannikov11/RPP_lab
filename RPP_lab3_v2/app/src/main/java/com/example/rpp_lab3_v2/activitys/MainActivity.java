package com.example.rpp_lab3_v2.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rpp_lab3_v2.R;
import com.example.rpp_lab3_v2.dataBase.DB;


public class MainActivity extends AppCompatActivity {

    Button btn_show;
    Button btn_add;
    Button btn_change;
    Button btn_change_v;
    private boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getInt("DBVersion") != 0 && savedInstanceState.getInt("DBVersion") != -1)
            DB.version = savedInstanceState.getInt("DBVersion");
        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        setContentView(R.layout.activity_main);

        btn_show = findViewById(R.id.btn_show);
        btn_add = findViewById(R.id.btn_add);
        btn_change = findViewById(R.id.btn_change);
        btn_change_v = findViewById(R.id.btn_swap);
        final DB db = new DB(getApplicationContext());

        DB dbHelper = new DB(getApplicationContext());
        if (savedInstanceState != null)
            created = savedInstanceState.getBoolean("created");
        if (!created) {
            dbHelper.addFiveStudents();
            created = true;
        }


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListActivity(db.version);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB dbhelper = new DB(getApplicationContext());
                if (db.getNewVersion() == 2)
                    dbhelper.addStudent_v2();
                else
                    dbhelper.addStudent_v1();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB dbhelper = new DB(getApplicationContext());
                if (db.getNewVersion() == 2)
                    dbhelper.changeStudent_v2();
                else
                    dbhelper.changeStudent_v1();
            }
        });

        btn_change_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getNewVersion() == 2)
                    db.setNewVersion(1);
                else
                    db.setNewVersion(2);
                DB dbhelper = new DB(getApplicationContext(), db.getNewVersion());
                Toast toast = Toast.makeText(getApplicationContext(), "Была установлена БД версии " + Integer.toString(DB.version), Toast.LENGTH_LONG);
                toast.show();
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

    public void startListActivity(int version){
        if (version == 1) {
            Intent intent = new Intent(MainActivity.this, ListActivity_v1.class);
            startActivity(intent);
        }
        else if (version == 2){
            Intent intent = new Intent(MainActivity.this, ListActivity_v2.class);
            startActivity(intent);
        }
    }

}
