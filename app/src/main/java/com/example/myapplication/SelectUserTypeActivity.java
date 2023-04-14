package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectUserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_user_type);

        ConstraintLayout seeker = findViewById(R.id.seeker);
        ConstraintLayout employer = findViewById(R.id.employer);
        ConstraintLayout agency = findViewById(R.id.agency);

        Button next = findViewById(R.id.cta_next);

        seeker.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.green_corners);
            employer.setBackgroundResource(R.drawable.corners);
            agency.setBackgroundResource(R.drawable.corners);
            next.setVisibility(View.VISIBLE);
        });

        employer.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.corners);
            employer.setBackgroundResource(R.drawable.green_corners);
            agency.setBackgroundResource(R.drawable.corners);
            next.setVisibility(View.VISIBLE);
        });

        agency.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.corners);
            employer.setBackgroundResource(R.drawable.corners);
            agency.setBackgroundResource(R.drawable.green_corners);
            next.setVisibility(View.VISIBLE);
        });

        next.setOnClickListener( event -> {
//            TODO SUITE REGISTER
//            Intent intent = new Intent (this, HomeActivity.class);
//            startActivity(intent);
        });



    }
}