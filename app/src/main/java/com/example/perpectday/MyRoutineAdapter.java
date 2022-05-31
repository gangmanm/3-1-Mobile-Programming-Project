package com.example.perpectday;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyRoutineAdapter extends RecyclerView.Adapter<MyRoutineAdapter.MyViewHolder> {


    int count_first =0;
    Context context;
    int check = 0;
    ArrayList<NewRoutine> newRoutineArrayList;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyRoutineAdapter(Context context, ArrayList<NewRoutine> newRoutineArrayList) {
        this.context = context;
        this.newRoutineArrayList = newRoutineArrayList;
    }

    @NonNull
    @Override
    public MyRoutineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.routine, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRoutineAdapter.MyViewHolder holder, int position) {

        NewRoutine newRoutine = newRoutineArrayList.get(position);

        holder.mycheckbox.setText(newRoutine.getContent());



        if(newRoutine.getCheck()==1) {
            holder.mycheckbox.setChecked(true);


        }

        //체크하면 1 올라가고 아니면 1 내림
        holder.mycheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (holder.mycheckbox.isChecked()) {
                    // TODO : CheckBox is checked.
                    newRoutine.setCheck(1);
                    System.out.println("체크되었습니다.");
                    Map<String, Object> checkDetail = new HashMap<>();
                    checkDetail.put("check", 1);
                    db.collection("NewRoutine").whereEqualTo("uid", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                db.collection("NewRoutine")
                                        .whereEqualTo("content", newRoutine.getContent())
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful() && !task.getResult().isEmpty()) {

                                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                            String documentID = documentSnapshot.getId();
                                            db.collection("NewRoutine").document(documentID).update(checkDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                }
                                            });
                                        }

                                    }


                                });
                            }
                        }
                    });
                } else {
                    // TODO : CheckBox is unchecked.
                    newRoutine.setCheck(0);
                    System.out.println("체크되었습니다.");
                    Map<String, Object> checkDetail = new HashMap<>();
                    checkDetail.put("check", 0);
                    db.collection("NewRoutine").whereEqualTo("uid", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                db.collection("NewRoutine")
                                        .whereEqualTo("content", newRoutine.getContent())
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful() && !task.getResult().isEmpty()) {

                                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                            String documentID = documentSnapshot.getId();
                                            db.collection("NewRoutine").document(documentID).update(checkDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                }
                                            });
                                        }

                                    }


                                });
                            }
                        }
                    });

                }



                }


        });




        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("루틴을 삭제하시겠습니까?");
                builder.setMessage("한번 삭제시 되돌릴 수 없습니다");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (user.getUid().toString().equals(newRoutine.getUid())) {

                            db.collection("NewRoutine").whereEqualTo("uid", user.getUid())
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                        db.collection("NewRoutine")
                                                .whereEqualTo("content", newRoutine.getContent())
                                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                                    String documentID = documentSnapshot.getId();
                                                    db.collection("NewRoutine").document(documentID).delete()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_LONG).show();
                                                                    newRoutineArrayList.remove(position);
                                                                    notifyItemRemoved(position);
                                                                    notifyItemRangeChanged(position, newRoutineArrayList.size());
                                                                }
                                                            });
                                                }

                                            }


                                        });
                                    } else {
                                        Toast.makeText(context, "작성자만 삭제할 수 있습니다.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(context, "작성자만 삭제할 수 있습니다.", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return newRoutineArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView routine;
        CheckBox mycheckbox;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mycheckbox = itemView.findViewById(R.id.check1_2);
            delete = itemView.findViewById(R.id.delete_routine_card);
        }
    }
}
