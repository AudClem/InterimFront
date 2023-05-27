package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        List<String> nomList = new ArrayList<>();
        List<Integer> nomListImg = new ArrayList<>();

        TextView profile = findViewById(R.id.profile_type);

        String user_type = "Administrator";

        /* TODO récupérer l'ID de l'utilisateur et faire la requête pour avoir le type d'utilisateur à mettre dans user_type */

        if(user_type == "Simple User") {
            profile.setText("Simple User");
            nomList.addAll(Arrays.asList("Browse", "Notifications", "Edit Profile", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.magnifying_glass, R.drawable.bell, R.drawable.edit_profile, R.drawable.gear));
        }
        else if (user_type == "Job Seeker") {
            profile.setText("Job Seeker");
            nomList.addAll(Arrays.asList("Browse", "Notifications", "My Applications", "My Jobs", "Groups", "Edit Profile", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.magnifying_glass, R.drawable.bell, R.drawable.applications, R.drawable.jobs, R.drawable.groups, R.drawable.edit_profile, R.drawable.gear));
        }
        else if (user_type == "Employer") {
            profile.setText("Employer");
            nomList.addAll(Arrays.asList("Post", "Notifications", "Edit Profile", "Upgrade", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.applications, R.drawable.bell, R.drawable.edit_profile, R.drawable.upgrade, R.drawable.gear));
        }
        else if (user_type == "Agency") {
            profile.setText("Agency");
            nomList.addAll(Arrays.asList("Post", "Notifications", "Edit Profile", "Upgrade", "Settings"));
            nomListImg.addAll(Arrays.asList(R.drawable.applications, R.drawable.bell, R.drawable.edit_profile, R.drawable.upgrade, R.drawable.gear));
        }
        else if (user_type == "Moderator") {
            profile.setText("Moderator");
            nomList.addAll(Arrays.asList("Statistics", "Reports"));
            nomListImg.addAll(Arrays.asList(R.drawable.statistics, R.drawable.reports));
        }
        else if (user_type == "Administrator") {
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