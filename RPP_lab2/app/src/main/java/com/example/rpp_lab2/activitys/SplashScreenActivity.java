package com.example.rpp_lab2.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.rpp_lab2.models.Techno;
import com.example.rpp_lab2.network.GetDataService;
import com.example.rpp_lab2.network.RetrofitModule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private ArrayList<Techno> technologies;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        technologies = new ArrayList(); //Создаем список, в котором будут храниться полученные данные

        GetDataService service = RetrofitModule.getApiService();//
        Call<ArrayList<Techno>> call = service.getAllInfo();
        call.enqueue(new Callback<ArrayList<Techno>>() {
            @Override
            public void onResponse(Call<ArrayList<Techno>> call, Response<ArrayList<Techno>> response) {
                ArrayList<Techno> list = response.body();
                putTech(list);
            }
            @Override
            public void onFailure(Call<ArrayList<Techno>> call, Throwable t) {
                Toast.makeText(SplashScreenActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
/* Метод заполнения массива с технологиями, *
 * который   исюлючает   пустые   элементы  *
 * И осуществляет переход к MainActivity    */
    public void putTech(ArrayList<Techno> tech){
        for (Techno t : tech) {
            if (t.getName() != null && !t.getName().isEmpty() ){
                technologies.add(t);
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("techArray", technologies);
        startActivity(intent);
        finish();
    }
}
