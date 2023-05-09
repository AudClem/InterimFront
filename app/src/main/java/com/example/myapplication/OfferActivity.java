package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_offer);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            String userId = extras.getString("userID");
            System.out.println( "userid : " + userId );
        }
    }
}