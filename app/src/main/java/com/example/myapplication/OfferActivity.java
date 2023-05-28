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
                showDialog();
            }
        });

        EditText search = (EditText) findViewById(R.id.search);
        Intent intent1 = new Intent (this, JobSearch.class);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    startActivity(intent1);
                    return true;
                }

                return false;
            }
        });

        RecyclerView rc = findViewById(R.id.recyclerview);
        List<ItemOffer> items = new ArrayList<ItemOffer>();
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));

        rc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // ajouté this
        rc.setAdapter(new CustomAdaptaterRecycler(getApplicationContext(), items, this));



        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            userId = extras.getString("userId");
            System.out.println("ddffdf" + userId);
        }

        ImageView profileimg = findViewById(R.id.profileimg);
        profileimg.setOnClickListener(event -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_job_detail);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.79));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onItemClick(int position) {
        // grace a la position en parametre, on pourra afficher un modal contenant differentes info selon la position de l'item cliqué. Probablement en utilisant des listes toujours.
        showDialog();
    }

}