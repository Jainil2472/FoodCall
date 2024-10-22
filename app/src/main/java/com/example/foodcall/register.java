package com.example.foodcall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity {
    EditText firstNameEditText,lastNameEditText,emailEditText,phoneEditText,birthDateEditText,passwordEditText,confirmPassword;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        signupButton = findViewById(R.id.login);

        // Bind views
        firstNameEditText = findViewById(R.id.first_name);
         lastNameEditText = findViewById(R.id.last_name);
         emailEditText = findViewById(R.id.email);
         phoneEditText = findViewById(R.id.phoneno);
         passwordEditText = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.conformpassword);





        signupButton.setOnClickListener(v -> {
            // Get user input values
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phoneNo = phoneEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPasswordStr = confirmPassword.getText().toString();

            // Validate the input
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || password.isEmpty() || confirmPasswordStr.isEmpty()) {
                Toast.makeText(register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPasswordStr)) {
                Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create the user map
            Map<String, Object> user = new HashMap<>();
            user.put("first_name", firstName);
            user.put("last_name", lastName);
            user.put("email", email);
            user.put("phone", phoneNo);
            user.put("password", password);
            Toast.makeText(register.this, "User: " + firstName, Toast.LENGTH_SHORT).show();

            // Add a new document with a generated ID
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                            // Optionally, redirect to another activity
                             startActivity(new Intent(register.this, login.class));
                             finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Firestore", "Error adding document", e);
                            Toast.makeText(register.this, "Registration Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


    }



}
