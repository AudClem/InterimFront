package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

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
            System.out.println( res.getString("message") );

            if( res.getCode() == 200 ){
                //TO DO REDIRECTION
            }

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