package com.example.foodcall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    Button login;
    EditText email,password;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        login = (Button) findViewById(R.id.login);
        email =(EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.create);
        String myemail = email.getText().toString();
        String mypassword = password.getText().toString();
        String jainil = "jainill";
        String pass ="pass";
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().equals(jainil) && password.getText().toString().equals(pass)){
                    Intent intent = new Intent(login.this, homepage.class);
                startActivity(intent);
                }else{
                    Toast tost = Toast.makeText(login.this,"User name or Email is wrong try again",Toast.LENGTH_SHORT);
                    tost.show();
                }

            }
        });

    }
}