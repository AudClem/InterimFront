package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterCheckActivity extends AppCompatActivity {

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_check);

        Button cta_browsing = findViewById(R.id.cta_profile);
        Button cta_complete_profile = findViewById(R.id.cta_complete_profile);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userID = extras.getString("userId");
        }

        cta_browsing.setOnClickListener( event -> {
            Intent intent = new Intent (this, OfferActivity.class);
            intent.putExtra("userId", userID );
            startActivity(intent);
        });

        cta_complete_profile.setOnClickListener( event -> {
            Intent intent = new Intent (this, SelectUserTypeActivity.class);
            intent.putExtra("userId", userID );
            startActivity(intent);
        });
    }
}