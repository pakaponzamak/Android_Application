package com.example.myloginapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ActivityTeacher extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        ListView mListView = (ListView) findViewById(R.id.student_list);

        //Student Object
        StudentData pakapon = new StudentData("pakapon","19","19.00");
        StudentData baramee = new StudentData("baramee","20","18.00");


        ArrayList<StudentData> studentList = new ArrayList<>();
        studentList.add(pakapon);
        studentList.add(baramee);

        StudentListAdapter adapter = new StudentListAdapter(this,R.layout.student_list_layout,studentList);
        mListView.setAdapter(adapter);



}
}
