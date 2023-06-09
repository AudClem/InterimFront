package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        EditText mail = findViewById( R.id.mail );
        EditText password = findViewById( R.id.password );
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button cta = findViewById( R.id.cta );

        cta.setOnClickListener( event -> {
            String mailStr = String.valueOf( mail.getText() );
            String passwordStr = String.valueOf( password.getText() );

            Request.Response res = login( mailStr, passwordStr );
            System.out.println( res.getString( 0,"message") );

            if( res.getCode() == 200 ){
                System.out.println("Login" + res.getString( 0,"id"));
                Intent intent = new Intent (this, OfferActivity.class);
                intent.putExtra("userId", res.getString( 0,"id") );
                startActivity(intent);
            }

        });

        TextView register = findViewById(R.id.register);

        register.setOnClickListener( event -> {
            Intent intent = new Intent (this, RegisterActivity.class);
            startActivity(intent);
        });

    }
    private Request.Response login(String email, String password ){
        String url="http://10.0.2.2:5000/aut/login";

        Request.Body data = new Request.Body();
        data.put("email", email );
        data.put("password", password );

        return Request.post( url, data );
    }
}