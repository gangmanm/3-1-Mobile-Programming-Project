package com.example.perpectday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Routine_Rcycle extends AppCompatActivity {




        RecyclerView recyclerView;
        ArrayList<NewRoutine> newRoutineArrayList;
        MyRoutineAdapter myRoutineAdapter;
        FirebaseFirestore db;
        NewRoutine newRoutine;
        EditText post_routine;
        public static int count_ru=0;
        public static int ary_count=0;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        public static String title="";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_routine_rcycle);
            db = FirebaseFirestore.getInstance();




            recyclerView = findViewById(R.id.routineRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            PhOffsetItemDecoration itemDecoration = new PhOffsetItemDecoration(10);
            recyclerView.addItemDecoration(itemDecoration);
            post_routine = (EditText)findViewById(R.id.inputRoutine);


            newRoutine = new NewRoutine();


            newRoutineArrayList = new ArrayList<NewRoutine>();
            myRoutineAdapter = new MyRoutineAdapter(com.example.perpectday.Routine_Rcycle.this,newRoutineArrayList);

            recyclerView.setAdapter(myRoutineAdapter);
            newRoutineArrayList.clear();
            EventChangeListener();


            TextView community = findViewById(R.id.community2);
            community.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), PostList.class);
                    startActivity(intent);
                }
            });

            ImageView imageBack=findViewById(R.id.imageBack);
            imageBack.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {
                    // startActivityForResult(new Intent(getApplicationContext(),PostList.class),
                    // 1);
                    onBackPressed();

                }
            });




            // If Click the AddButton add the new Routine in the FireStore database
            ImageView imageAddComment=findViewById(R.id.add_routine);
            imageAddComment.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {
                    newRoutine.setContent(post_routine.getText().toString());

                    newRoutine.setUid(user.getUid());

                    newRoutine.setCheck(0);

                    db.collection("NewRoutine")
                            .add(newRoutine);

                    Toast.makeText(Routine_Rcycle.this,"data inserted succefully",Toast.LENGTH_LONG).show();
                    post_routine.setText("");
                    count_routine();

                }
            });

            TextView profile = findViewById(R.id.profile);

            //count the checked routine and, total routine to calculate success rate
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User_Profile.count_array = count_array();
                    User_Profile.count_routine =count_routine();

                    Intent intent = new Intent(getApplicationContext(), Calendar.class);
                    startActivity(intent);

                }
            });



        }


    // EventChangeListener get the Routine from the
    // Firestore database every time that user call the function and save the data in the newRoutineArrayList
        private void EventChangeListener() {
            db.collection("NewRoutine").whereEqualTo("uid",user.getUid())
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {

                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(error != null)
                            {
                                Log.e("Firestore error",error.getMessage());
                                return;


                            }
                            for (DocumentChange dc : value.getDocumentChanges())
                            {
                                if(dc.getType() == DocumentChange.Type.ADDED)
                                {
                                    newRoutineArrayList.add(dc.getDocument().toObject(NewRoutine.class));
                                }
                                myRoutineAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }

        public int count_routine()
        {
            int count=0;

            for(int i=0; i<newRoutineArrayList.size(); i++)
            {
                count+=newRoutineArrayList.get(i).getCheck();
            }


            User_Profile.count_routine = count;
            return count;
        }

        public int count_array()
        {
            ary_count = newRoutineArrayList.size();
            User_Profile.count_array = ary_count;
            return newRoutineArrayList.size();
        }


}