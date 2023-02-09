package com.example.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityStudent extends AppCompatActivity {

    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private TextView username;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String KEY_TIME = "Time selected";
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
            Toast.makeText(ActivityStudent.this,mSelectedDate, Toast.LENGTH_SHORT).show();
        });
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            // Handle time change event here
            // For example, you can show a Toast to display the selected time
            time = hourOfDay + "."+minute;

        });

        Button schedule_button = findViewById(R.id.scheduleButton);
        schedule_button.setOnClickListener(v -> {
            DocumentReference docRef = db.collection("My user").document(userFromMainActivity);
            Map<String, Object> dateAndTime = new HashMap<>();
            dateAndTime.put(KEY_TIME,time);
            dateAndTime.put(KEY_DATE,mSelectedDate);
            docRef.update(dateAndTime).addOnSuccessListener(aVoid -> {
                Toast.makeText(ActivityStudent.this,mSelectedDate + " " + time, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "DocumentSnapshot successfully written!");
            })
                    .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
        });

    }
}