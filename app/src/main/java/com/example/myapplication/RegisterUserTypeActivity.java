package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user_type);

        List<String> nomList = new ArrayList<>();
        List<Integer> nomListImg = new ArrayList<>();

        TextView title = findViewById(R.id.registerAccount);
        ImageView leftArrow = findViewById(R.id.imageView);

        Bundle bundle = getIntent().getExtras();
        int choice = bundle.getInt("choice");

        if(choice == 1) {
            //Job Seeker
            title.setText("Job Seeker");
            nomList.addAll(Arrays.asList("First Name", "Last Name", "Job Title", "Location", "Start", "Employer"));
            nomListImg.addAll(Arrays.asList(R.drawable.user, R.drawable.user, R.drawable.bag, R.drawable.location, R.drawable.calendar, R.drawable.employersvg));
        }
        else if (choice == 2) {
            //Employer
            title.setText("Employer");
            nomList.addAll(Arrays.asList("Business Name", "Department Name", "National Number", "Name #1", "Name #2", "First Phone Number", "Second Phone Number"));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.business, R.drawable.id, R.drawable.user, R.drawable.user, R.drawable.phone, R.drawable.phone));
        }
        else if (choice == 3) {
            //Agency
            title.setText("Agency");
            nomList.addAll(Arrays.asList("Agency Name", "National Number", "Name #1", "Name #2", "First Phone Number", "Second Phone Number"));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.id, R.drawable.user, R.drawable.user, R.drawable.phone, R.drawable.phone));
        }

        ListView listv;
        listv = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), nomList, nomListImg);
        listv.setAdapter(customBaseAdapter);

        leftArrow.setOnClickListener( event -> {
            finish();
        });

    }
}