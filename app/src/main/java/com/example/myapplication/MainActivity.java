package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = new Intent (this, JobApplication.class);
        intent.putExtra("userId", "b2cf324f-6b25-4074-8420-050441e73691");
        intent.putExtra("offreId", "7aa99801-3ef8-4a51-9caf-786438f3fdbe");
        startActivity(intent);
    }
}