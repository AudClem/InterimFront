package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class JobApplication extends AppCompatActivity {

    private String userId;
    private String offreId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_job_application);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
            offreId = extras.getString("offreId");
        }

        Button cta = findViewById( R.id.cta );
        cta.setOnClickListener( event -> {
            Request.Response res = post();
            if( res.getCode() == 200 ){
                System.out.println( res.getString(0,"message") );
            }
        });
    }

    private Request.Response post(){
        String url="http://10.0.2.2:5000/app/create";
        Request.Body data = new Request.Body();
        data.put( "id_user", userId );
        data.put( "id_offre", offreId );

        return Request.post( url, data );
    }
}