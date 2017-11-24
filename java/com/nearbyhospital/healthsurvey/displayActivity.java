package com.nearbyhospital.healthsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class displayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle b = getIntent().getExtras();
        ArrayList<String>patientList = b.getStringArrayList("patientList");
        ArrayList<String>caseList = b.getStringArrayList("caseList");
        ArrayList<String>detailList = b.getStringArrayList("detailList");
        ArrayList<String>testrList = b.getStringArrayList("testrList");
        ArrayList<String>arList = b.getStringArrayList("arList");
        ArrayList<String>cellnoList = b.getStringArrayList("cellnoList");
        ArrayList<String>addressList = b.getStringArrayList("addressList");


        final TextView tv10= (TextView) findViewById(R.id.tv10);
        final Button b10 =(Button) findViewById(R.id.b10);
        final Button b102 =(Button) findViewById(R.id.b102);


        for(int i = 0; i < patientList.size(); i++) {
            tv10.append("patient_name: "+patientList.get(i)+"   ,case_no: "+caseList.get(i)+"    , case_details: "+detailList.get(i)+"   ,test_required: "+testrList.get(i)+"   ,additional_requirements: "+arList.get(i)+"   ,patients_cellno: "+cellnoList.get(i)+"   ,patient_address: "+addressList.get(i)+"   \n\n\n\n");
        }

        b10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent plistIntent = new Intent(displayActivity.this, surveyActivity.class);
                displayActivity.this.startActivity(plistIntent);


            }


        });

        b102.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent plistIntent = new Intent(displayActivity.this, loginActivity.class);
                displayActivity.this.startActivity(plistIntent);


            }


        });


    }
}

