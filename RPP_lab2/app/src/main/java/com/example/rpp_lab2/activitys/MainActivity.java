package com.example.rpp_lab2.activitys;

import android.app.ProgressDialog;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rpp_lab2.R;
import com.example.rpp_lab2.adapters.RecyclerAdapter;
import com.example.rpp_lab2.fragments.ListFragment;
import com.example.rpp_lab2.fragments.PagerFragment;
import com.example.rpp_lab2.models.Techno;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Techno> technologies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        technologies = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList("techArray");//Получили созданный в сплешактивити список

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentByTag("list_fragment") == null){

            ListFragment listFragment = new ListFragment();
            Bundle bundleRecycler = new Bundle();
            bundleRecycler.putParcelableArrayList("techArray", technologies);
            listFragment.setArguments(bundleRecycler);

            PagerFragment pagerFragment = new PagerFragment();
            Bundle bundlePager = new Bundle();
            bundlePager.putParcelableArrayList("techArray", technologies);
            pagerFragment.setArguments(bundlePager);

            fragmentManager.beginTransaction()
                    .add(R.id.mainActivity, pagerFragment, "pager_fragment")
                    .add(R.id.mainActivity, listFragment, "list_fragment")
                    .commit();
            fragmentManager.popBackStackImmediate();
            setRecyclerViewFragment();
        }

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void setRecyclerViewFragment(){
        getSupportFragmentManager().beginTransaction()
                .show(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("list_fragment")))
                .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("pager_fragment")))
                .commit();
    }

    public void setViewPagerFragment(int position){
        try {
            PagerFragment pagerFragment = (PagerFragment) getSupportFragmentManager().findFragmentByTag("pager_fragment");
            pagerFragment.setPosition(position);
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .show(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("pager_fragment")))
                    .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("list_fragment")))
                    .commit();
        }catch (Exception ex){
            Log.e("Ошибка вызова ViewPager", ex.getMessage());
        }
    }
}

