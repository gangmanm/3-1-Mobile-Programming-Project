package com.example.perpectday;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{
    ArrayList<String>dayList;
    OnItemClickListener onItemClickListener;

    public CalendarAdapter(ArrayList<String> dayList, OnItemClickListener onItemClickListener){

        this.dayList = dayList;
        this.onItemClickListener = onItemClickListener;
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
        //날짜 적용// 아마 여기에 데이터가 들어가면 되지 않을까,,
        //날짜 변수에 담기
        String day = dayList.get(position);
        holder.dateTxt.setText(day);

        //토요일, 일요일 텍스트 색상 변경
        if((position + 1)%7==0) { //토요일
            holder.dateTxt.setTextColor(Color.BLUE);
        }
        else if (position == 0 || position % 7 == 0){//일요일
            holder.dateTxt.setTextColor(Color.RED);

        }
        //날짜 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //인터페이스를통해 날짜를 넘겨줌.
                onItemClickListener.onItemClick(day);
            }
        });


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