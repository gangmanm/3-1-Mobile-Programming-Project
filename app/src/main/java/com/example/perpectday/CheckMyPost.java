package com.example.perpectday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CheckMyPost extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<NewPost> newPostArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_post);

        recyclerView = findViewById(R.id.noteRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        db = FirebaseFirestore.getInstance();
        newPostArrayList = new ArrayList<NewPost>();
        myAdapter = new MyAdapter(CheckMyPost.this,newPostArrayList);
        recyclerView.setAdapter(myAdapter);


        EventChangeListener();
        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                startActivityForResult(new Intent(getApplicationContext(),PostList.class),
                        1);

            }
        });


    }


    private void EventChangeListener() {
        db.collection("NewPost").whereEqualTo("uid",user.getUid())
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
                                newPostArrayList.add(dc.getDocument().toObject(NewPost.class));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}