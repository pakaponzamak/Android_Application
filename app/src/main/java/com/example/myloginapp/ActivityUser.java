package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityUser extends AppCompatActivity {

    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private String mSelectedDate;
    private int date,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mTimePicker = findViewById(R.id.timePicker);
        mDatePicker = findViewById(R.id.datePicker);

        mDatePicker.init(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            date = calendar.get(Calendar.DATE);
            month = calendar.get(Calendar.MONTH);
           // year = calendar.get(Calendar.YEAR);


            SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
            mSelectedDate = dateFormat.format(calendar.getTime());
        });


        mTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            // Handle time change event here
            // For example, you can show a Toast to display the selected time
            Toast.makeText(ActivityUser.this,
                    "Time selected: "+ mSelectedDate+ " at " + hourOfDay + ":" + minute,
                    Toast.LENGTH_SHORT).show();
        });
    }
}