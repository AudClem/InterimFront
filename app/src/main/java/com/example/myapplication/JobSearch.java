package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_job_search);

        //Bundle bundle = getIntent().getExtras();
        //int choice = bundle.getInt("choice");

        List<String> ssTitre = new ArrayList<>();
        List<String> titre = new ArrayList<>();
        List<String> info = new ArrayList<>();

        List<Integer> print = new ArrayList<>();
        List<Integer> star = new ArrayList<>();


        ssTitre.addAll(Arrays.asList("Lorem INC 1.", "Lorem INC 2.", "Lorem INC 3.", "Lorem INC 4.", "Lorem INC 5.", "Lorem INC 6."));
        titre.addAll(Arrays.asList("Lorem Ipsum 1.", "Lorem Ipsum 2.", "Lorem Ipsum 3.", "Lorem Ipsum 4.", "Lorem Ipsum 5.", "Lorem Ipsum 6."));
        info.addAll(Arrays.asList("$1500/m - Auburn, Alabama 1.", "$1500/m - Auburn, Alabama 2.", "$1500/m - Auburn, Alabama 3.", "$1500/m - Auburn, Alabama 4.", "$1500/m - Auburn, Alabama 5.", "$1500/m - Auburn, Alabama 6."));


        print.addAll(Arrays.asList(R.drawable.print, R.drawable.print, R.drawable.print, R.drawable.print, R.drawable.print, R.drawable.print));
        star.addAll(Arrays.asList(R.drawable.blackstar, R.drawable.blackstar, R.drawable.blackstar, R.drawable.blackstar, R.drawable.blackstar, R.drawable.blackstar));


        ListView listvv = (ListView) findViewById(R.id.customListViewJob);
        BaseAdapterJobSearch customBaseAdapterj = new BaseAdapterJobSearch(getApplicationContext(), ssTitre, titre, info, print, star);
        listvv.setAdapter(customBaseAdapterj);

    }
}