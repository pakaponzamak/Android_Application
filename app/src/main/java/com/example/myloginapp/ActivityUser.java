package com.example.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityUser extends AppCompatActivity {

    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private TextView username;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String KEY_TIME = "Time";
    public static final String KEY_DATE = "Date";


    public String time = "";
    public String mSelectedDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        mTimePicker = findViewById(R.id.timePicker);
        mDatePicker = findViewById(R.id.datePicker);
        //Username display on top and data from Main
        Intent intent = getIntent();
        String userFromMainActivity = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        username = findViewById(R.id.textView6);
        username.setText("Username : " +userFromMainActivity);

        mDatePicker.init(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
            mSelectedDate = dateFormat.format(calendar.getTime());
            Toast.makeText(ActivityUser.this,mSelectedDate, Toast.LENGTH_SHORT).show();
        });
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            // Handle time change event here
            // For example, you can show a Toast to display the selected time
            /*if(hourOfDay == 0){
                hourOfDay = 12;
            }
            String AM_PM;
            if(hourOfDay < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }*/

            time = "Time selected : "+ hourOfDay + "."+minute;

            //Toast.makeText(ActivityUser.this,time, Toast.LENGTH_SHORT).show();
        });

        Button schedule_button = findViewById(R.id.scheduleButton);
        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityUser.this,mSelectedDate + " " + time, Toast.LENGTH_SHORT).show();
                DocumentReference docRef = db.collection("My user").document(userFromMainActivity);
                Map<String, Object> dateAndTime = new HashMap<>();
                dateAndTime.put(KEY_TIME,time);
                dateAndTime.put(KEY_DATE,mSelectedDate);
                docRef.update(dateAndTime).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ActivityUser.this,mSelectedDate + " " + time, Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }
        });



/*
        Map<String, Object> dateAndTime = new HashMap<>();
        dateAndTime.put(KEY_TIME,time);
        //dateAndTime.put("state", "CA");

        db.collection("My user").document("1")
                .set(dateAndTime)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        Toast.makeText(ActivityUser.this,time, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });*/
    }
}