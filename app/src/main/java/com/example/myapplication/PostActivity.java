package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class PostActivity extends AppCompatActivity {
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_post);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
        }

        Button cta = findViewById( R.id.cta );
        cta.setOnClickListener( event -> {
            Request.Response res = post();
            if( res.getCode() == 200 ){
                System.out.println( res.getString(0,"message") );
            }
        });

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener( event -> {
            onBackPressed();
        });
    }

    private Request.Response post(){
        String url="http://10.0.2.2:5000/offer/create";
        Request.Body data = new Request.Body();
        data.put( "id_user", userId );
        data.put( "name", String.valueOf(((EditText)findViewById( R.id.txtName )).getText()) );
        data.put( "metier", String.valueOf(((EditText)findViewById( R.id.txtJobTitle )).getText()) );
        data.put( "txtLocation", String.valueOf(((EditText)findViewById( R.id.txtLocation )).getText()) );
        data.put( "dateDeb", String.valueOf(((EditText)findViewById( R.id.txtStart )).getText()) );
        data.put( "dateFin", String.valueOf(((EditText)findViewById( R.id.txtEnd )).getText()) );
        data.put( "remuneration", String.valueOf(((EditText)findViewById( R.id.txtRemuneration )).getText()) );
        return Request.post( url, data );
    }
}