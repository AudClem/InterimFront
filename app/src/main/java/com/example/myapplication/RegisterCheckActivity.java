package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class RegisterCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_check);

        Button cta_browsing = findViewById(R.id.cta_browsing);
        Button cta_complete_profile = findViewById(R.id.cta_complete_profile);

        cta_browsing.setOnClickListener( event -> {
            // TO DO START OFFER ACTIVITY
//            Intent intent = new Intent (this, LoginActivity.class);
//            startActivity(intent);
        });

        cta_complete_profile.setOnClickListener( event -> {
            // TO DO SELECT USER TYPE
//            Intent intent = new Intent (this, LoginActivity.class);
//            startActivity(intent);
        });
    }
}