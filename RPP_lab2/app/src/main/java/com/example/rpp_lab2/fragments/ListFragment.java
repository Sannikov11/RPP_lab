
package com.example.rpp_lab2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rpp_lab2.R;
import com.example.rpp_lab2.activitys.MainActivity;
import com.example.rpp_lab2.adapters.RecyclerAdapter;
import com.example.rpp_lab2.listeners.RecyclerListener;
import com.example.rpp_lab2.models.Techno;
import com.example.rpp_lab2.network.GetDataService;
import com.example.rpp_lab2.network.RetrofitModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        ArrayList<Techno> technologies = getArguments().getParcelableArrayList("techArray");
        RecyclerAdapter recyclerViewAdapter = new RecyclerAdapter(technologies);
        RecyclerView mainRecyclerView = view.findViewById(R.id.customRecyclerView);

        mainRecyclerView.setAdapter(recyclerViewAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}


