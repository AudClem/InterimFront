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
    private final int EMPLOYER = 2;
    private final int AGENCY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user_type);

        List<String> nomList = new ArrayList<>();
        List<Integer> nomListImg = new ArrayList<>();


        TextView title = findViewById(R.id.registerAccount);
        ImageView leftArrow = findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();
        int choice = bundle.getInt("choice");

        System.out.println("choice : " + choice);
        Button cta_signUp = findViewById(R.id.cta);

        if(choice == JOB_SEEKER) {
            title.setText("Job Seeker");
            nomList.addAll(Arrays.asList("First Name", "Last Name", "Birthday (yyyy-mm-dd)", "Location", "Nationnality"));
            nomListImg.addAll(Arrays.asList(R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.location, R.drawable.user));
        }
        else if (choice == EMPLOYER) {
            title.setText("Employer");
            nomList.addAll(Arrays.asList("National Number", "Last Name #1", "Last Name #2", "mail #2", "Phone Number #1", "Phone Number #2", "Adress", "Business Name", "Departement", "Sub Departement" ));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.user, R.drawable.user, R.drawable.mail, R.drawable.phone, R.drawable.phone, R.drawable.location, R.drawable.business, R.drawable.business, R.drawable.business));
        }
        else if (choice == AGENCY) {
            title.setText("Agency");
            nomList.addAll(Arrays.asList("National Number", "Last Name #1", "Last Name #2", "mail #2", "Phone Number #1", "Phone Number #2", "Adress", "Agency Name" ));
            nomListImg.addAll(Arrays.asList(R.drawable.business, R.drawable.user, R.drawable.user, R.drawable.mail, R.drawable.phone, R.drawable.phone, R.drawable.location, R.drawable.business));
        }

        for (int i = 0; i < nomList.size(); i++){
            System.out.println(( nomList.get(i) ));
        }
        System.out.println(( '\n' ));


        ListView listv = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), nomList, nomListImg );
        listv.setAdapter(customBaseAdapter);

        leftArrow.setOnClickListener( event -> {
            onBackPressed();
        });

        cta_signUp.setOnClickListener( event -> {
            String userId = bundle.getString("userID");
            Request.Body data = new Request.Body();
            System.out.println("choice : " + choice);
            System.out.println( listv.getCount()) ;
            if( choice == JOB_SEEKER ){
                data.put("id", userId );
                data.put("firstname", getDataFromListv(listv, 0) );
                data.put("lastname", getDataFromListv(listv, 1) );
                data.put("birthday", getDataFromListv(listv, 2) );
                data.put("city", getDataFromListv(listv, 3) );
                data.put("nationality", getDataFromListv(listv, 4) );
            }
            else if( choice == EMPLOYER ){
                data.put("id", userId );
                data.put("numberN", getDataFromListv(listv, 0) );
                data.put("lastname1", getDataFromListv(listv, 1) );
                data.put("lastname2", getDataFromListv(listv, 2) );
                data.put("mail2", getDataFromListv(listv, 3) );
                data.put("phone1", getDataFromListv(listv, 4) );
                data.put("phone2", "" );
                data.put("adress", "" );
                data.put("businessName", "" );
                data.put("depServ", "" );
                data.put("depSServ", "" );
                data.put("type", String.valueOf( choice ) );
            }
            else if( choice == AGENCY ){
                data.put("id", userId );
                data.put("numberN", getDataFromListv(listv, 0) );
                data.put("lastname1", getDataFromListv(listv, 1) );
                data.put("lastname2", getDataFromListv(listv, 2) );
                data.put("mail2", getDataFromListv(listv, 3) );
                data.put("phone1", getDataFromListv(listv, 4) );
                data.put("phone2", "" );
                data.put("adress", "" );
                data.put("agencyName", "" );
                data.put("type", String.valueOf( choice ) );
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
        System.out.println(v);
        et = (EditText) v.findViewById(R.id.text);
        return et.getText().toString();
    }
}