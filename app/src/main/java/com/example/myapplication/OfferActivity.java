package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_offer);

        //Button test = findViewById(R.id.button);
        // test.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
              //  showDialog();
            //}
        //});

        RecyclerView rc = findViewById(R.id.recyclerview);
        List<ItemOffer> items = new ArrayList<ItemOffer>();
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));
        items.add(new ItemOffer("Lorem INC.", "Lorem Ipsum",R.drawable.print, "$1500/m -", "Auburn, Alabama", R.drawable.greenstar));

        rc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rc.setAdapter(new CustomAdaptaterRecycler(getApplicationContext(), items));



        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            String userId = extras.getString("userID");
            System.out.println( "userid : " + userId );
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_job_detail);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.75));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}