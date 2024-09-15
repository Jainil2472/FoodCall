package com.example.foodcall;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.foodcall.R;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set HomeFragment as the default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, new home_fragment()).commit();
        }

        // Use setOnNavigationItemSelectedListener for BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.
                }

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new home_fragment();
                        break;

                    case R.id.nav_cart:
                        selectedFragment = new cart_fragment();
                        break;

                    default:
                        selectedFragment = new home_fragment();
                        break;
                }

                // Replace the current fragment with the selected one
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, selectedFragment).commit();
                }

                return true;  // Return true to indicate item selection handled
            }
        });
    }
}
