package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private String userId;
    private int userRole;
    private int SIMPLE_USER = 0;
    private int JOB_SEEKER = 1;
    private int EMPLOYER = 2;
    private int AGENCY = 3;
    private int MODERATOR = 4;
    private int ADMINISTRATOR = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        List<String> nomList = new ArrayList<>();
        List<Integer> nomListImg = new ArrayList<>();

        TextView profile = findViewById(R.id.profile_type);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
        }
        System.out.println("http://10.0.2.2:5000/user?id=" + userId);
        Request.Response res = Request.get("http://10.0.2.2:5000/user?id=" + userId);
        userRole = Integer.parseInt(res.getString( 0,"userRole"));
        System.out.println( res.getCode() );

        if(userRole == SIMPLE_USER) {
            profile.setText("Simple User");
            nomList.addAll(Arrays.asList("Browse", "Notifications", "Complete Profile", "Edit Profile", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.magnifying_glass, R.drawable.bell, R.drawable.edit_profile, R.drawable.edit_profile, R.drawable.gear));
        }
        else if (userRole == JOB_SEEKER) {
            profile.setText("Job Seeker");
            nomList.addAll(Arrays.asList("Browse", "Notifications", "My Applications", "My Jobs", "Groups", "Edit Profile", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.magnifying_glass, R.drawable.bell, R.drawable.applications, R.drawable.jobs, R.drawable.groups, R.drawable.edit_profile, R.drawable.gear));
        }
        else if (userRole == EMPLOYER) {
            profile.setText("Employer");
            nomList.addAll(Arrays.asList("Post", "Notifications", "Edit Profile", "Upgrade", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.applications, R.drawable.bell, R.drawable.edit_profile, R.drawable.upgrade, R.drawable.gear));
        }
        else if (userRole == AGENCY) {
            profile.setText("Agency");
            nomList.addAll(Arrays.asList("Post", "Notifications", "Edit Profile", "Upgrade", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.applications, R.drawable.bell, R.drawable.edit_profile, R.drawable.upgrade, R.drawable.gear));
        }
        else if (userRole == MODERATOR) {
            profile.setText("Moderator");
            nomList.addAll(Arrays.asList("Statistics", "Reports"));
            nomListImg.addAll(Arrays.asList(R.drawable.statistics, R.drawable.reports));
        }
        else if (userRole == ADMINISTRATOR) {
            profile.setText("Administrator");
            nomList.addAll(Arrays.asList("Statistics", "Reports", "Bans"));
            nomListImg.addAll(Arrays.asList(R.drawable.statistics, R.drawable.reports, R.drawable.bans));
        }


        ListView listv;
        listv = (ListView) findViewById(R.id.customListView);
        BaseAdapterProfile customBaseAdapter = new BaseAdapterProfile(getApplicationContext(), nomList, nomListImg);
        listv.setAdapter(customBaseAdapter);

        /* TODO faire les redirections des TextViews */

    }
}