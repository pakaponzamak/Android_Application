package com.example.myloginapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private String enteredUsername;
    private String enteredPassword;
    private TextView forgot_password;
    public static final String EXTRA_MESSAGE = "Send it to user activity";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.username);
        mPasswordEditText = findViewById(R.id.password);

        MaterialButton loginbtn = findViewById(R.id.loginbtn);



        loginbtn.setOnClickListener(v -> checkCredentials());

        MaterialButton sign_in = findViewById(R.id.sign_in);
        sign_in.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"SIGN-IN!!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    private void checkCredentials() {
        enteredUsername = mUsernameEditText.getText().toString();
        enteredPassword = mPasswordEditText.getText().toString();
        if(!(enteredUsername.equals("") || enteredPassword.equals(""))) {
            DocumentReference docRef = db.collection("My user").document(enteredUsername);
            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    String mUsername = document.getString("username");
                    String mPassword = document.getString("password");
                    String mRole = document.getString("role");

                    if (enteredUsername.equals(mUsername) && enteredPassword.equals(mPassword)) {

                        if(mRole.equals("Teacher")) {
                            Toast.makeText(MainActivity.this, "Correct. You login as a " + mRole, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ActivityTeacher.class);
                            //intent.putExtra(EXTRA_MESSAGE, mUsername);
                            startActivity(intent);
                        }if(mRole.equals("Student")){
                            Toast.makeText(MainActivity.this, "Correct. You login as a " + mRole, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ActivityStudent.class);
                            intent.putExtra(EXTRA_MESSAGE, mUsername);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect. Try again", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "get failed with", task.getException());
                }
            });
        }
        else {
            Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        mUsernameEditText.setText("");
        mPasswordEditText.setText("");

    }
}