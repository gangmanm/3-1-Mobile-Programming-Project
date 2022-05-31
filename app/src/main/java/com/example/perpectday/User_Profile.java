package com.example.perpectday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class User_Profile extends AppCompatActivity {

    private int CurrentProgress  = 0;
    public static int count_routine=0;
    public static int count_array=0;
    private ProgressBar progressBar;
    private Button startProgress;
    ArrayList<NewRoutine> newRoutineArrayList;
    MyRoutineAdapter myRoutineAdapter;
    FirebaseFirestore db;
    NewRoutine newRoutine;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        db = FirebaseFirestore.getInstance();

        CurrentProgress = count_routine;
        progressBar = findViewById(R.id.progressbar);
        progressBar.setProgress(CurrentProgress);
        progressBar.setMax(count_array);

        TextView pro = findViewById(R.id.clear_pro);
        pro.setText(count_routine +"/"+count_array+"Clear!");


        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                // startActivityForResult(new Intent(getApplicationContext(),PostList.class),
                // 1);
                onBackPressed();

            }
        });




    }




}