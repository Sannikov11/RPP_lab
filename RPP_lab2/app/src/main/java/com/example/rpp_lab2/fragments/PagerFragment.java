package com.example.rpp_lab2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpp_lab2.R;
import com.example.rpp_lab2.adapters.PagerAdapter;
import com.example.rpp_lab2.models.Techno;
import com.example.rpp_lab2.network.GetDataService;
import com.example.rpp_lab2.network.RetrofitModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagerFragment extends Fragment {
    private ViewPager viewPager;

    public PagerFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        ArrayList<Techno> technologies = getArguments().getParcelableArrayList("techArray");
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), technologies);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @NonNull
    public void setPosition(int position){
        viewPager.setCurrentItem(position);
    }

}

