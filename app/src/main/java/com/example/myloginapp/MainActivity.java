package com.example.myloginapp;

import static android.content.ContentValues.TAG;
import static com.example.myloginapp.RegisterActivity.KEY_USERNAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

public class MainActivity extends AppCompatActivity {


    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private String enteredUsername;
    private String enteredPassword;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mUsernameEditText = findViewById(R.id.username);
        mPasswordEditText = findViewById(R.id.password);

        MaterialButton loginbtn = findViewById(R.id.loginbtn);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        MaterialButton sign_in = (MaterialButton) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"SIGN-IN!!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkCredentials() {
        enteredUsername = mUsernameEditText.getText().toString();
        enteredPassword = mPasswordEditText.getText().toString();
        if(!(enteredUsername.equals("") || enteredPassword.equals(""))) {
            DocumentReference docRef = db.collection("My user").document(enteredUsername);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        String mUsername = document.getString("username");
                        String mPassword = document.getString("password");

                        if (enteredUsername.equals(mUsername) && enteredPassword.equals(mPassword)) {
                            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ActivityUser.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect. Try again", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
        else {
            Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
        }
    }
}