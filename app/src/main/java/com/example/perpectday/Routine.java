package com.example.perpectday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Routine extends AppCompatActivity {

    Button Button_routineAdd, Button_planAdd;
    Dialog dialog_routineAdd, dialog_planAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        Button_routineAdd = findViewById(R.id.Button_routineAdd);
        Button_planAdd = findViewById(R.id.Button_planAdd);

        dialog_routineAdd = new Dialog(Routine.this);       // Dialog 초기화
        dialog_routineAdd.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog_routineAdd.setContentView(R.layout.activity_routine_dialog);

        Button_routineAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog01();
            }
        });
    }

    public void showDialog01(){
        dialog_routineAdd.show(); // 다이얼로그 띄우기

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

        // 위젯 연결 방식은 각자 취향대로~
        // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
        // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        // OK 버튼
        Button Button_routineOK = dialog_routineAdd.findViewById(R.id.Button_routineOK);
        Button_routineOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                //edit text에서 입력받은 값 저장
                //체크박스 추가하고 거기에 값 할당
                finish();           // 앱 종료
            }
        });

        // Cancel 버튼
        Button Button_routineCancel = dialog_routineAdd.findViewById(R.id.Button_routineCancel);
        Button_routineCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dialog_routineAdd.dismiss(); // 다이얼로그 닫기
            }
        });

    }
}