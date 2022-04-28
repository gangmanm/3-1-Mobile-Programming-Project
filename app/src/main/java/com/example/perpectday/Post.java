package com.example.perpectday;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post extends AppCompatActivity {
EditText post_title,post_subtitle,post_content;
NewPost newpost;
FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_title = (EditText)findViewById(R.id.inputNoteTitle);
        post_subtitle = (EditText)findViewById(R.id.inputNoteSubtitle);
        post_content = (EditText)findViewById(R.id.content);

        //Pring New Post Class
        newpost = new NewPost();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
         DatabaseReference conditionRef = mRootRef.child("txt");

        ImageView imageBack=findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                onBackPressed();
            }
        });


        EditText NoteTitle  = (EditText)findViewById(R.id.inputNoteTitle);

        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               newpost.setTitle(post_title.getText().toString());
               newpost.setSubtitle(post_subtitle.getText().toString());
               newpost.setContent(post_content.getText().toString());
                db.collection("NewPost")
                        .add(newpost);

                Toast.makeText(Post.this,"data inserted succefully",Toast.LENGTH_LONG).show();
            }
        });
    }
}