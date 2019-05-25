package com.example.rpp_lab2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpp_lab2.R;
import com.squareup.picasso.Picasso;

public class PageFragment extends Fragment {
    TextView nameText, helpText;
    ImageView imageView;
    private String title;
    private String help;
    private String icon;
    private int page;


    public static PageFragment newInstance(int page, String title, String help, String icon) {
        PageFragment PageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title",title);
        args.putString("help", help);
        args.putString("icon", icon);
        PageFragment.setArguments(args);
        return PageFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment, container, false);

        nameText = view.findViewById(R.id.pagerNameTextView);
        helpText = view.findViewById(R.id.helpTextView);
        imageView = view.findViewById(R.id.pageIconImageView);
        nameText.setText(title);
        helpText.setText(help);

        Picasso.get().load(icon).into(imageView);

        return view;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("page", 0);
        title = getArguments().getString("title");
        help = getArguments().getString("help");
        icon = getArguments().getString("icon");
    }
}


