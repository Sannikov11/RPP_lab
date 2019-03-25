package com.example.firstlabrpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActList = (Button) findViewById(R.id.openListBtn);// привязал кнопку
        btnActList.setOnClickListener(this); // метод для отклика кнопки
    }

    public void onClick(View v){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);//указываю к какому активити перейти
        startActivity(intent); // запуск нового активити
    }

}
