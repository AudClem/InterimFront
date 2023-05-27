package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUserTypeActivity extends AppCompatActivity {

    private final int JOB_SEEKER = 1;
    private final int EMPLOYER = 1;
    private final int AGENCY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user_type);

        List<String> nomList = new ArrayList<>();
        List<Integer> nomListImg = new ArrayList<>();

        List<String> sousnomList = new ArrayList<>();
        List<Integer> sousnomListImg = new ArrayList<>();

        TextView title = findViewById(R.id.registerAccount);
        ImageView leftArrow = findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();
        int choice = bundle.getInt("choice");

        Button cta_signUp = findViewById(R.id.cta);

        if(choice == JOB_SEEKER) {
            title.setText("Job Seeker");
            nomList.addAll(Arrays.asList("First Name", "Last Name", "Birthday (yyyy-mm-dd)", "Location", "Nationnality"));
            nomListImg.addAll(Arrays.asList(R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.location, R.drawable.user));
        }
        else if (choice == EMPLOYER) {
            title.setText("Employer");
            nomList.addAll(Arrays.asList("Business Name", "Department Name", "National Number", "Name #1", "Name #2", "First Phone Number", "Second Phone Number"));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.business, R.drawable.id, R.drawable.user, R.drawable.user, R.drawable.phone, R.drawable.phone));
        }
        else if (choice == AGENCY) {
            title.setText("Agency");
            nomList.addAll(Arrays.asList("Agency Name", "National Number", "Name #1", "Name #2", "First Phone Number", "Second Phone Number"));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.id, R.drawable.user, R.drawable.user, R.drawable.phone, R.drawable.phone));
        }

        ListView listv = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), nomList, nomListImg, sousnomList, sousnomListImg);
        listv.setAdapter(customBaseAdapter);

        leftArrow.setOnClickListener( event -> {
            onBackPressed();
        });

        cta_signUp.setOnClickListener( event -> {
            String userId = bundle.getString("userID");
            Request.Body data = new Request.Body();

            if( choice == JOB_SEEKER ){
                data.put("id", userId );
                data.put("firstname", getDataFromListv(listv, 0) );
                data.put("lastname", getDataFromListv(listv, 1) );
                data.put("birthday", getDataFromListv(listv, 2) );
                data.put("city", getDataFromListv(listv, 3) );
                data.put("nationality", getDataFromListv(listv, 4) );
            }
            else if( choice == EMPLOYER ){

            }
            else if( choice == AGENCY ){

            }

            Request.Response res = signUpPlus( choice, data );

            if( res.getCode() == 201 ){
                Intent intent = new Intent (this, RegisterCheckFinalActivity.class);
                intent.putExtra("userID", userId );
                startActivity(intent);
            }



        });

    }

    private Request.Response signUpPlus( int userType, Request.Body data ){
        String url="http://10.0.2.2:5000/aut/";

        if( userType == JOB_SEEKER ) url += "signup-jobseeker";
        else if( userType == EMPLOYER || userType == AGENCY ) url += "signup-sub";

        return Request.post( url, data );
    }

    private String getDataFromListv( ListView listv, int index ){
        View v;
        EditText et;
        v = listv.getChildAt( index );
        et = (EditText) v.findViewById(R.id.text);
        return et.getText().toString();
    }
}