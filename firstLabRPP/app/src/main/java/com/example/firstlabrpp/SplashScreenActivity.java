package com.example.firstlabrpp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timer = new Thread(){     //создаем новый поток
            public void run(){

                try{
                    sleep(2000); // приостановка потока на 2 сек
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    Intent myIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(myIntent); // переход к активити main
                }
            }
        };
        timer.start();// запуск метода таймер
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause(); //ставим сплеш активити на паузу
//        this.finish();  //убиваем активити
//    }
}
