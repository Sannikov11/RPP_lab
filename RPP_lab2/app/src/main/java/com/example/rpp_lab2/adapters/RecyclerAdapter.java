package com.example.rpp_lab2.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpp_lab2.R;
import com.example.rpp_lab2.activitys.MainActivity;
import com.example.rpp_lab2.models.Techno;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private ArrayList<Techno> dataList;
    private Context context;

    public RecyclerAdapter(ArrayList<Techno> dataList) {
        super();
        this.dataList = dataList;
    }

    public RecyclerAdapter(Context context, ArrayList<Techno> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.txtTitle.setText(dataList.get(position).getName());
        Picasso.get().load(dataList.get(position).getGraphic()).into(holder.coverImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (MainActivity) v.getContext() ).setViewPagerFragment(position);
            }
        });
        holder.txtTitle.setText(dataList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        if (dataList != null) return dataList.size();
        else return 0;
    }
}
