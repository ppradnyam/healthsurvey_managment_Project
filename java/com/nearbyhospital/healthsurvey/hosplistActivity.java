package com.nearbyhospital.healthsurvey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class hosplistActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosplist);
        Bundle b = getIntent().getExtras();
        ArrayList<String> hospList = b.getStringArrayList("hospNamelist");
        ArrayList<String> idList = b.getStringArrayList("hospIdList");

        final TextView tv61 = (TextView) findViewById(R.id.tv61);
        final Button b61 =(Button) findViewById(R.id.b61);
        final Button b62 =(Button) findViewById(R.id.b62);

        for(int i = 0; i < hospList.size(); i++) {
            tv61.append("Hospital name: "+hospList.get(i)+"   , ID:"+idList.get(i)+"\n\n\n ");
        }

        b62.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent plistIntent = new Intent(hosplistActivity.this, loginActivity.class);
                hosplistActivity.this.startActivity(plistIntent);


            }


        });

        b61.setOnClickListener(new View.OnClickListener() {

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




                Intent hlistIntent = new Intent(hosplistActivity.this, hospinfoActivity.class);
                hosplistActivity.this.startActivity(hlistIntent);


            }


        });


    }
}
