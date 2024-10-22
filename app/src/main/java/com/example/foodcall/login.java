package com.example.foodcall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class login extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView createAccountTextView;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Make sure this matches your layout file name

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Bind views
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        createAccountTextView = findViewById(R.id.create); // Bind create account text view if needed

        // Set up the login button click listener
        loginButton.setOnClickListener(v -> loginUser());

        // Set up create account text view click listener if necessary
        createAccountTextView.setOnClickListener(v -> {
            // Navigate to register activity
            startActivity(new Intent(login.this, register.class)); // Replace RegisterActivity with your actual registration activity
        });
    }
    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference usersRef = db.collection("users");
        usersRef.whereEqualTo("email", email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            boolean userFound = false;

                            for (DocumentSnapshot document : task.getResult()) {
                                String dbPassword = document.getString("password");

                                if (dbPassword != null && dbPassword.equals(password)) {
                                    userFound = true;
                                    Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                                    // **Save login state in SharedPreferences**
                                    getSharedPreferences("user_prefs", MODE_PRIVATE)
                                            .edit()
                                            .putBoolean("is_logged_in", true)
                                            .apply();

                                    // Navigate to homepage
                                    startActivity(new Intent(login.this, homepage.class));
                                    finish(); // Finish login activity
                                    break;
                                }
                            }
                            if (!userFound) {
                                Toast.makeText(login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login.this, "Error getting user data", Toast.LENGTH_SHORT).show();
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    }
