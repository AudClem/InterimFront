package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {

    private String userID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText username = findViewById( R.id.username );
        EditText mail = findViewById( R.id.mail );
        EditText password = findViewById( R.id.password );
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button cta = findViewById( R.id.cta );

        cta.setOnClickListener( event -> {
            String usernameStr = String.valueOf( username.getText() );
            String mailStr = String.valueOf( mail.getText() );
            String passwordStr = String.valueOf( password.getText() );

            Request.Response res = signUp(usernameStr, mailStr, passwordStr);

            System.out.println("Response code : " + res.getCode());
            System.out.println("Message : " + res.getString( 0,"message") );
            if( res.getCode() == 201 ){
                userID = res.getString( 0,"id");

                Intent intent = new Intent (this, RegisterCheckActivity.class);
                intent.putExtra("userID", userID );
                startActivity(intent);
            }
        });


        ImageView back = findViewById(R.id.back);
        back.setOnClickListener( event -> {
            onBackPressed();
        });
    }

    private Request.Response signUp( String username, String email, String password ){
        String url="http://10.0.2.2:5000/aut/signup";

        Request.Body data = new Request.Body();
        data.put("username", username );
        data.put("email", email );
        data.put("password", password );

        return Request.post( url, data );
    }
}