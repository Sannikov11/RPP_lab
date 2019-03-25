package com.example.firstlabrpp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Integer> numberList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView idNumber;
        private LinearLayout linLayout;

        public ViewHolder( View itemView) {
            super(itemView);
            idNumber = itemView.findViewById(R.id.idNumber);
            linLayout = itemView.findViewById(R.id.idLayout);
        }
    }

    public MyAdapter(Context context, List<Integer> number ){
        this.context = context;
        this.numberList = number;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {  //возвращает объект ViewHolder, который будет хранить данные по одному объекту
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) { //выполняет привязку объекта ViewHolder к объекту по определенной позиции
        int item = numberList.get(i); // номер элемента списка
        if(item % 2 == 0)   //выборка цвета лейаута (серый / белый)
            viewHolder.linLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        else
            viewHolder.linLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.idNumber.setText(MyParser.getParsedString(item)); //перевод из чисел в слова
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }


}