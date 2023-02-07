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



}
}
