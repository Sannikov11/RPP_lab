package com.example.firstlabrpp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    private List<Integer> numberList; // список номеров элементов
    private RecyclerView recView; //компонент для отображения элементов списка
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recView = (RecyclerView) findViewById(R.id.itemsOfList);//привязываю к контейнеру
        numberList = new ArrayList<>(); // заполняю список элементами от 1 до 1000000

        for (int i = 1; i < 1000001; i++) {
            numberList.add(i);
        }

        MyAdapter adapter = new MyAdapter(getApplicationContext(), numberList); //
        RecyclerView.LayoutManager  manager = new LinearLayoutManager(getApplicationContext()); // layoutManager отвечает за отображения данных
                                                                                                //  в данном случае обычный список
        recView.setLayoutManager(manager);  // устанавливаю для ресвью разметку
        recView.setAdapter(adapter);    // передаем необходимые данные и задаем стиль указанный в классе адаптера
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
