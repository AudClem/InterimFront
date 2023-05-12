package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterCheckFinalActivity extends AppCompatActivity {

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_check_final);

        Button cta_profile = findViewById(R.id.cta_profile);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userID = extras.getString("userID");
        }

        cta_profile.setOnClickListener( event -> {
            //TODO redirect to Profile
            //Intent intent = new Intent (this, OfferActivity.class);

        });
    }
}