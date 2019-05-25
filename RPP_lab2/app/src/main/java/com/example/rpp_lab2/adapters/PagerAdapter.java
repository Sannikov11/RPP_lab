package com.example.rpp_lab2.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rpp_lab2.fragments.PageFragment;
import com.example.rpp_lab2.models.Techno;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Techno> technologies;

    public PagerAdapter(FragmentManager fm, ArrayList<Techno> l) {
        super(fm);
        this.technologies = l;
    }

    @Override
    public Fragment getItem(int i) {
        return PageFragment.newInstance(i, technologies.get(i).getName(), technologies.get(i).getHelptext(), technologies.get(i).getGraphic());
    }

    @Override
    public int getCount() {
        return technologies.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  technologies.get(position).getName();
    }
}