package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectUserTypeActivity extends AppCompatActivity {

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_user_type);

        Intent intent = new Intent (this, RegisterUserTypeActivity.class);
        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userID = extras.getString("userID");
        }

        ConstraintLayout seeker = findViewById(R.id.seeker);
        ConstraintLayout employer = findViewById(R.id.employer);
        ConstraintLayout agency = findViewById(R.id.agency);

        Button next = findViewById(R.id.cta_next);

        ImageView leftArrow = findViewById(R.id.previous);

        seeker.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.green_corners);
            employer.setBackgroundResource(R.drawable.corners);
            agency.setBackgroundResource(R.drawable.corners);
            next.setVisibility(View.VISIBLE);
            intent.putExtra("choice", 1);
        });

        employer.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.corners);
            employer.setBackgroundResource(R.drawable.green_corners);
            agency.setBackgroundResource(R.drawable.corners);
            next.setVisibility(View.VISIBLE);
            intent.putExtra("choice", 2);
        });

        agency.setOnClickListener( event -> {
            seeker.setBackgroundResource(R.drawable.corners);
            employer.setBackgroundResource(R.drawable.corners);
            agency.setBackgroundResource(R.drawable.green_corners);
            next.setVisibility(View.VISIBLE);
            intent.putExtra("choice", 3);
        });

        next.setOnClickListener( event -> {
            intent.putExtra("userID", userID );
            startActivity(intent);
        });

        leftArrow.setOnClickListener( event -> {
            finish();
        });



    }
}