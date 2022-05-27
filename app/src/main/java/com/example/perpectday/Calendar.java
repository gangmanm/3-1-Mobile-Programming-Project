package com.example.perpectday;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calendar extends AppCompatActivity implements OnItemClickListener {
    TextView curMonthTxt; //년월 텍스트뷰
    LocalDate selectedDate; //년월 변수 (날짜 정보만 가져올 때 사용하는 함수)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        curMonthTxt = findViewById(R.id.curMonthTxt);
//        ImageButton preBtn = (ImageButton)findViewById(R.id.preBtn);
//        ImageButton nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        Button preBtn = (Button)findViewById(R.id.prevButton);
        Button nextBtn = (Button)findViewById(R.id.nextButton);
        recyclerView = findViewById(R.id.recyclerView);

        //Current Date
        selectedDate = LocalDate.now();

        //Set Display
        setMonthView();

        //Button of Previous Month
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 월 변수에 -1 해서 담기
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        //Button of Next Month
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();

            }
        });
    }//onCreate

    //현재 날짜를 받아서 형식을 바꾼뒤 리턴해줌(형식 재지정)
    private String currentDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월");
        return date.format(formatter);
    }

    //set display
    private void setMonthView() {
        //년, 월 텍스트뷰
        curMonthTxt.setText(currentDate(selectedDate));
        //해당 월 날짜 가져오기
        ArrayList<String> dayList = dayInMonth(selectedDate);
        //어뎁터데이터 적용
        CalendarAdapter adapter = new CalendarAdapter(dayList, Calendar.this);

        //setting layout(column = 7)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        //레이아웃 적용
        recyclerView.setLayoutManager(manager);

        //어뎁터 적용
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> dayInMonth(LocalDate date){
        ArrayList<String> dayList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        //해당 월 마지막 날짜 가져오기(28, 30, 31)
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월 첫번째 날 가져오기
        LocalDate firstDay = selectedDate.withDayOfMonth(1);
        //첫번째 날 요일 가져오기
        int eOfWeek = firstDay.getDayOfWeek().getValue();

        for(int i = 1; i<42;i++){
            if(i <= eOfWeek || i > lastDay + eOfWeek) {
                dayList.add("");
            }
            else{
                dayList.add(String.valueOf(i-eOfWeek));
            }
        }
        return dayList;
    }
    //인터페이스 구현(날짜 어뎁터에서 넘겨준 날짜를 받는다
    @Override
    public void onItemClick(String dayText) {
        String yearMonthDay = currentDate(selectedDate) + " " +dayText + "일";
        Toast.makeText(this, yearMonthDay, Toast.LENGTH_LONG).show();
    }
}
