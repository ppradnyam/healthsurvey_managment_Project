package com.nearbyhospital.healthsurvey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
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

public class pdataActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdata);

        Bundle b = getIntent().getExtras();
        ArrayList<String>caseList = b.getStringArrayList("caseList");
        ArrayList<String>virusList = b.getStringArrayList("virusList");

        final TextView tv81 = (TextView) findViewById(R.id.tv81);
        final Button b81 =(Button) findViewById(R.id.b81);
        final Button b82 =(Button) findViewById(R.id.b82);

        for(int i = 0; i < caseList.size(); i++) {
            tv81.append("cases: "+caseList.get(i)+"   , virus:"+virusList.get(i)+"\n\n\n");
        }

        b81.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar=new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("loading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressBarStatus=0;



                Intent hlistIntent = new Intent(pdataActivity.this, getpdataActivity.class);
                pdataActivity.this.startActivity(hlistIntent);


            }


        });

        b82.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent hlistIntent = new Intent(pdataActivity.this, loginActivity.class);
                pdataActivity.this.startActivity(hlistIntent);


            }


        });


    }
}


