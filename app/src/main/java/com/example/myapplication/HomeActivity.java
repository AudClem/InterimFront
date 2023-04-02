package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        Button cta = findViewById(R.id.cta);

        cta.setOnClickListener( event -> {
            // TO DO START OFFER ACTIVITY
//            Intent intent = new Intent (this, LoginActivity.class);
//            startActivity(intent);
        });
    }
}