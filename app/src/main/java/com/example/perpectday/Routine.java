package com.example.perpectday;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Routine extends AppCompatActivity {

    Button Button_routineAdd, Button_planAdd;
    Dialog dialog_routineAdd, dialog_planAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        //----------routine-------------
        Button_routineAdd = findViewById(R.id.Button_routineAdd);
        //
        dialog_routineAdd = new Dialog(com.example.perpectday.Routine.this);       // Dialog 초기화
        dialog_routineAdd.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog_routineAdd.setContentView(R.layout.activity_routine_dialog);
        //
        Button_routineAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog01();
            }
        });
    }

    public void showDialog01(){
        dialog_routineAdd.show(); // 다이얼로그 띄우기

        //OK 버튼
        Button Button_routineOK = dialog_routineAdd.findViewById(R.id.Button_routineOK);
        Button_routineOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCheckBox01();
                dialog_routineAdd.dismiss();    // 다이얼로그 닫기
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

    public void createCheckBox01(){
        LinearLayout ll = findViewById(R.id.LinearLayout_RoutineList);

        // 1. 체크박스 객체 생성
        CheckBox checkboxView = new CheckBox(getApplicationContext());

        // 2. 체크박스에 들어갈 문자 설정
        EditText ee = dialog_routineAdd.findViewById(R.id.EditText_routine);
        String name = ee.getText().toString();
        checkboxView.setText(name);
        checkboxView.setTextSize(20);

        // 3. 체크박스 id 설정
        checkboxView.setId(0);

        // 4. 레이아웃 설정
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // 5. 설정한 레이아웃에 체크박스 적용
        checkboxView.setLayoutParams(param);

        // 6. 생성 및 설정된 체크박스 레이아웃에 적용
        ll.addView(checkboxView);
    }
}