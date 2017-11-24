package com.nearbyhospital.healthsurvey;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class surveyActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);


        final Button b51 = (Button) findViewById(R.id.b51);
        final Button b52 = (Button) findViewById(R.id.b52);
        final EditText et51 = (EditText) findViewById(R.id.et51);

        b52.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent plistIntent = new Intent(surveyActivity.this, loginActivity.class);
                surveyActivity.this.startActivity(plistIntent);


            }


        });



        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                progressBar=new ProgressDialog(V.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("loading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressBarStatus=0;


                final String area_pincode = et51.getText().toString();
                final ArrayList<String> hospNameList = new ArrayList<String>();
                final ArrayList<String> hospIdList = new ArrayList<String>();

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HC", "Response:"+response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int length = jsonResponse.getInt("length");

                         if(success)
                         {
                            for (int i = 0; i < length; i++) {
                                JSONObject hospitalData = (JSONObject) jsonResponse.get(String.valueOf(i));
                                String hospitalName = hospitalData.getString("name");
                                int id = hospitalData.getInt("id");
                                hospNameList.add(hospitalName);
                                hospIdList.add(String.valueOf(id));
                            }
                            Intent hospListActivity = new Intent(surveyActivity.this, hosplistActivity.class);
                            Bundle b = new Bundle();
                            b.putStringArrayList("hospNamelist", hospNameList);
                            b.putStringArrayList("hospIdList", hospIdList);
                            hospListActivity.putExtras(b);
                            startActivity(hospListActivity);
                        }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(surveyActivity.this);
                                builder.setMessage("No such pincode data exists !!")
                                        .setNegativeButton("Retry with different pincode", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SurveyRequest surveyRequest = new SurveyRequest(area_pincode, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HC", String.valueOf(error));
                    }
                });
                Log.e("HC", "Sending request");
                RequestQueue queue = Volley.newRequestQueue(surveyActivity.this);

                queue.add(surveyRequest);
            }

        });
    }
}
