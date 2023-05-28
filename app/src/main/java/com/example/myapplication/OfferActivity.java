package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity implements RecyclerInterface {

    private List<ItemOffer> items;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_offer);

        ConstraintLayout recommended = findViewById(R.id.r_info_offer);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog();
            }
        });

        EditText search = (EditText) findViewById(R.id.search);
        Intent intent1 = new Intent (this, JobSearch.class);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    intent1.putExtra("userId", userId);
                    intent1.putExtra("searchStr", String.valueOf( search.getText() ));
                    startActivity(intent1);
                    return true;
                }

                return false;
            }
        });

        RecyclerView rc = findViewById(R.id.recyclerview);
        items = getPopular();

        rc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // ajouté this
        rc.setAdapter(new CustomAdaptaterRecycler(getApplicationContext(), items, this));



        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
        }

        ImageView profileimg = findViewById(R.id.profileimg);
        profileimg.setOnClickListener(event -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }


    private void showDialogP( ItemOffer item ) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_job_detail);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.79));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        ((TextView)dialog.findViewById( R.id.r_titre )).setText( item.sous_titre + ", Montpellier" );
        ((TextView)dialog.findViewById( R.id.titleJob )).setText( item.titre );
        ((TextView)dialog.findViewById( R.id.r_city )).setText( "Full Time, " + item.salaire + "€/h" );


        Button ctaJob = dialog.findViewById( R.id.ctaJob );
        ctaJob.setOnClickListener( event -> {
            Intent intent = new Intent (this, PostActivity.class);
            intent.putExtra("userId", userId );
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(int position) {
        // grace a la position en parametre, on pourra afficher un modal contenant differentes info selon la position de l'item cliqué. Probablement en utilisant des listes toujours.
        showDialogP( items.get(position) );
    }

    private List<ItemOffer> getPopular(){
        List<ItemOffer> items = new ArrayList<ItemOffer>();
        Request.Response popular = Request.get("http://10.0.2.2:5000/offer/popular");

        for (int i = 0; i < popular.length(); i++) {
            Request.Response offre = Request.get("http://10.0.2.2:5000/offer/?id=" + popular.getString(i, "id" ));
            Request.Response simple_user = Request.get("http://10.0.2.2:5000/user/?id=" + popular.getString(i, "id_utilisateurPayant" ));
            String username = simple_user.getString(0, "username");
            System.out.println(offre.getString(0, "metier"));

            items.add(new ItemOffer(username, offre.getString(0, "metier"),R.drawable.print, offre.getString(0, "remuneration") + "€/h", "Montpellier, France", R.drawable.greenstar));

        }

        return items;

    }

}