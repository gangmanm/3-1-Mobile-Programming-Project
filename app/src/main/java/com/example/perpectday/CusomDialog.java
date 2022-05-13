package com.example.perpectday;

import android.os.Bundle;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.perpectday.R;

class CustomDialog extends Dialog implements View.OnClickListener{
    //이건 그냥 에딧텍스트 값 받아오는 거 참고하고 삭제하자!
    private Button positiveButton;
    private Button negativeButton;
    private EditText editName;
    private Context context;
    private CustomDialogListener customDialogListener;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    //인터페이스 설정
    interface CustomDialogListener{
        void onPositiveClicked(String name);
        void onNegativeClicked();
    }

    //호출할 리스너 초기화
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routine);

        //init
        positiveButton = (Button)findViewById(R.id.Button_routineOK);
        negativeButton = (Button)findViewById(R.id.Button_routineCancel);
        editName = (EditText)findViewById(R.id.EditText_routine);

        //버튼 클릭 리스너 등록
        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button_routineOK: //확인 버튼을 눌렀을 때
                //각각의 변수에 EidtText에서 가져온 값을 저장
                String name = editName.getText().toString();

                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달
                customDialogListener.onPositiveClicked(name);
                dismiss();
                break;
            case R.id.Button_routineCancel: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }
    }
}