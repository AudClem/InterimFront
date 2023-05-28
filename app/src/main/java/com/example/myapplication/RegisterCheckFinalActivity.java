package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterCheckFinalActivity extends AppCompatActivity {

    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_check_final);

        Button cta_profile = findViewById(R.id.cta_profile);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
        }

        cta_profile.setOnClickListener( event -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);

        });
    }
}