package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_post);

        Button cta = findViewById( R.id.cta );

        cta.setOnClickListener( event -> {
            String txtName = String.valueOf(((EditText)findViewById( R.id.txtName )).getText());
            String txtJobTitle = String.valueOf(((EditText)findViewById( R.id.txtJobTitle )).getText());
            String txtLocation = String.valueOf(((EditText)findViewById( R.id.txtLocation )).getText());
            String txtStart = String.valueOf(((EditText)findViewById( R.id.txtStart )).getText());
            String txtEnd = String.valueOf(((EditText)findViewById( R.id.txtEnd )).getText());
            String txtRemuneration = String.valueOf(((EditText)findViewById( R.id.txtRemuneration )).getText());

        });
    }


}