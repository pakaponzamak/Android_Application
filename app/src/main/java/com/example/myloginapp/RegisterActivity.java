package com.example.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ROLE = "role";

    int teacherOrStudent = 0;
    public String role = "";
    public String time = "";
    public String refUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameEditText = findViewById(R.id.et_username);
        mPasswordEditText = findViewById(R.id.et_password);

        Button registerButton = findViewById(R.id.btn_register);

        registerButton.setOnClickListener(v -> {
            String username = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();

            DocumentReference docRef = db.collection("My user").document(username);
            docRef.get().addOnCompleteListener(task -> {
                DocumentSnapshot document = task.getResult();
                if (task.isSuccessful())
                    refUsername = document.getString("username");



                if(!(username.equals("") || password.equals("") || username.equals(refUsername))) {
                    if(teacherOrStudent == 1){
                        Map<String, Object> user = new HashMap<>();
                        user.put(KEY_USERNAME, username);
                        user.put(KEY_PASSWORD, password);
                        user.put(KEY_ROLE, role);
                        //user.put(KEY_TIME, time);
                        db.collection("My user").document(username).set(user)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(RegisterActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                    finish();
                                })

                                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                    }if(teacherOrStudent == 2){
                        Map<String, Object> user = new HashMap<>();
                        user.put(KEY_USERNAME, username);
                        user.put(KEY_PASSWORD, password);
                        user.put(KEY_ROLE, role);
                        db.collection("My user").document(username).set(user)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(RegisterActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                    finish();
                                })
                                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                    }if(teacherOrStudent != 2 && teacherOrStudent != 1)
                        Toast.makeText(RegisterActivity.this, "Please Choose Your Role", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Enter Something or username is already used", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "Enter Something or username is already used");
                }
            });

        });

    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.student_radio:
                if (checked) {
                    Toast.makeText(RegisterActivity.this, "Student Selected", Toast.LENGTH_SHORT).show();
                    role = "Student";
                    teacherOrStudent = 1;
                }
                break;
            case R.id.teacher_radio:
                if (checked) {
                    Toast.makeText(RegisterActivity.this, "Teacher Selected", Toast.LENGTH_SHORT).show();
                    role = "Teacher";
                    teacherOrStudent = 2;
                }
                break;
            default:
                // Do nothing.
                break;
        }
    }
}