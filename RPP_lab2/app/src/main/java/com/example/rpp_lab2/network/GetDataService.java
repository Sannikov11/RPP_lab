package com.example.rpp_lab2.network;

import com.example.rpp_lab2.models.Techno;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("techs.ruleset.json")
    Call<ArrayList<Techno>> getAllInfo();
}