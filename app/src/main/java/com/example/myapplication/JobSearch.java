package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSearch extends AppCompatActivity {

    private String userId;
    private String offreId;
    private String searchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_job_search);

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
            searchStr = extras.getString("searchStr");
        }

        List<String> ssTitre = new ArrayList<>();
        List<String> titre = new ArrayList<>();
        List<String> info = new ArrayList<>();

        List<Integer> print = new ArrayList<>();
        List<Integer> star = new ArrayList<>();

        Request.Response jobs = Request.get("http://10.0.2.2:5000/offer/search?name=" + searchStr);
        System.out.println("http://10.0.2.2:5000/offer/search?name=" + searchStr);
        System.out.println(jobs.length());

        for (int i = 0; i < jobs.length(); i++) {
            Request.Response offre = Request.get("http://10.0.2.2:5000/offer/?id=" + jobs.getString(i, "id" ));
            Request.Response simple_user = Request.get("http://10.0.2.2:5000/user/?id=" + jobs.getString(i, "id_utilisateurPayant" ));
            String username = simple_user.getString(0, "username");
            ssTitre.add(username);
            titre.add(offre.getString(0, "metier"));
            info.add(offre.getString(0, "remuneration") + "€/h");
            print.add(R.drawable.print);
            star.add(R.drawable.blackstar);
        }

        ListView listvv = (ListView) findViewById(R.id.customListViewJob);
        BaseAdapterJobSearch customBaseAdapterj = new BaseAdapterJobSearch(getApplicationContext(), ssTitre, titre, info, print, star);
        listvv.setAdapter(customBaseAdapterj);

        listvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogP( jobs, i );
            }
        });

    }

    private void showDialogP( Request.Response item, int i ) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_job_detail);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.79));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        Request.Response simple_user = Request.get("http://10.0.2.2:5000/user/?id=" + item.getString(i, "id_utilisateurPayant" ));
        String username = simple_user.getString(0, "username");
        ((TextView)dialog.findViewById( R.id.r_titre )).setText( username + ", Montpellier" );
        ((TextView)dialog.findViewById( R.id.titleJob )).setText( item.getString(i , "metier") );
        ((TextView)dialog.findViewById( R.id.r_city )).setText( "Full Time, " + item.getString(i , "remuneration") + "€/h" );


        Button ctaJob = dialog.findViewById( R.id.ctaJob );
        ctaJob.setOnClickListener( event -> {
            Intent intent = new Intent (this, JobApplication.class);
            intent.putExtra("userId", userId );
            intent.putExtra("offreId", item.getString(i, "id_offre"));
            startActivity(intent);
        });
    }

    private LinearLayout getDataFromListv(ListView listv, int index ){
        System.out.println("getDataFromListv");
        return (LinearLayout) listv.getAdapter().getView(index, null, listv);
    }
}