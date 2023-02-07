package com.example.myloginapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ActivityTeacher extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        ListView mListView = (ListView) findViewById(R.id.student_list);


        db.collection("My user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> studentList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult())
                        studentList.add(document.getId());
                    for(int i =0;i < 5;i++){
                    StudentData pakapon = new StudentData(studentList.get(i),"19","19.00");
                        ArrayList<StudentData> studentListt = new ArrayList<>();
                        studentListt.add(pakapon);
                        StudentListAdapter adapter = new StudentListAdapter(getApplicationContext(),R.layout.student_list_layout,studentListt);
                        mListView.setAdapter(adapter);
                    }

                    Toast.makeText(ActivityTeacher.this, studentList.get(1), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ActivityTeacher.this,"Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /*
        //Student Objects
        StudentData pakapon = new StudentData("pakapon","19","19.00");
        StudentData baramee1 = new StudentData("baramee","20","18.00");
        StudentData baramee2 = new StudentData("baramee","20","18.00");
        StudentData baramee3 = new StudentData("baramee","20","18.00");
        StudentData baramee4 = new StudentData("baramee","20","18.00");
        StudentData baramee5 = new StudentData("baramee","20","18.00");
        StudentData baramee6 = new StudentData("baramee","20","18.00");
        StudentData baramee7 = new StudentData("baramee","20","18.00");
        StudentData baramee8 = new StudentData("baramee","20","18.00");
        StudentData baramee9 = new StudentData("baramee","20","18.00");



        ArrayList<StudentData> studentList = new ArrayList<>();
        studentList.add(pakapon);
        studentList.add(baramee1);
        studentList.add(baramee2);
        studentList.add(baramee3);
        studentList.add(baramee4);
        studentList.add(baramee5);
        studentList.add(baramee6);
        studentList.add(baramee7);
        studentList.add(baramee8);
        studentList.add(baramee9);

        StudentListAdapter adapter = new StudentListAdapter(this,R.layout.student_list_layout,studentList);
        mListView.setAdapter(adapter);
*/


    }
}
