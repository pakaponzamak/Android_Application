package com.example.myloginapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ActivityTeacher extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String mCheck;
    private ArrayList<String> mStudentName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        ListView mListView = (ListView) findViewById(R.id.student_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mStudentName);

        mListView.setAdapter(arrayAdapter);

        db.collection("My user").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    mCheck = document.getString("role");
                    if(mCheck.equals("Student"))
                        mStudentName.add(document.getString("username") + "\nDate : " + document.getString("Date") + "  " + document.getString("Time"));
                    arrayAdapter.notifyDataSetChanged();

                }


            } else {
                Toast.makeText(ActivityTeacher.this,"Error", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
