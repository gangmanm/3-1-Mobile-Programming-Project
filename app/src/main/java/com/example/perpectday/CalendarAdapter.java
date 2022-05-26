package com.example.perpectday;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{
    ArrayList<String>dayList;

    public CalendarAdapter(ArrayList<String> dayList){
        this.dayList = dayList;
    }

    //화면 연결 메소드
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.date_cell, parent, false);
        return new CalendarViewHolder(view);
    }

    //data 연결 메서드
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        //날짜 적용
        holder.dateTxt.setText(dayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        //initialization
        TextView dateTxt;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTxt = itemView.findViewById(R.id.dateTxt);
        }
    }
}