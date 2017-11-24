package com.nearbyhospital.healthsurvey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




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

public class getpdataActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpdata);


        final EditText et91 = (EditText) findViewById(R.id.et91);
        final Button b91 = (Button) findViewById(R.id.b91);



        b91.setOnClickListener(new View.OnClickListener() {
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

                final String ID= et91.getText().toString();
                final ArrayList<String> patientList = new ArrayList<String>();
                final ArrayList<String> caseList = new ArrayList<String>();
                final ArrayList<String> detailList = new ArrayList<String>();
                final ArrayList<String> testrList = new ArrayList<String>();
                final ArrayList<String> arList = new ArrayList<String>();
                final ArrayList<String> cellnoList = new ArrayList<String>();
                final ArrayList<String> addressList = new ArrayList<String>();


                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HC", "Response:"+response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            int length = jsonResponse.getInt("length");
                            if(success)
                            {
                            for (int i = 0; i < length; i++) {
                                JSONObject caseData = (JSONObject) jsonResponse.get(String.valueOf(i));
                                String patient_name = caseData.getString("patient_name");
                                String case_no = caseData.getString("case_no");
                                String case_details = caseData.getString("case_details");
                                String test_required = caseData.getString("test_required");
                                String additional_requirements = caseData.getString("additional_requirements");
                                String patients_cellno = caseData.getString("patients_cellno");
                                String patient_address = caseData.getString("patient_address");

                                patientList.add(patient_name);
                                caseList.add(case_no);
                                detailList.add(case_details);
                                testrList.add(test_required);
                                arList.add(additional_requirements);
                                cellnoList.add(patients_cellno);
                                addressList.add(patient_address);
                            }
                            Intent getpdataActivity = new Intent(getpdataActivity.this, displayActivity.class);
                            Bundle b = new Bundle();
                            b.putStringArrayList("patientList", patientList);
                            b.putStringArrayList("caseList", caseList);
                            b.putStringArrayList("detailList", detailList);
                            b.putStringArrayList("testrList", testrList);
                            b.putStringArrayList("arList", arList);
                            b.putStringArrayList("cellnoList", cellnoList);
                            b.putStringArrayList("addressList", addressList);
                            getpdataActivity.putExtras(b);
                            startActivity(getpdataActivity);

                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getpdataActivity.this);
                            builder.setMessage("No data about patient exists for entered ID!!")
                                    .setNegativeButton("Retry with different ID", null)
                                    .create()
                                    .show();
                        }

                    } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                getpdataRequest hrequest = new getpdataRequest(ID, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HC", String.valueOf(error));
                    }
                });
                Log.e("HC", "Sending request");
                RequestQueue queue = Volley.newRequestQueue(getpdataActivity.this);

                queue.add(hrequest);
            }

        });
    }
}






