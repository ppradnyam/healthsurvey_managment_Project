package com.nearbyhospital.healthsurvey;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android. view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.os.Handler;


public class loginActivity extends AppCompatActivity {

    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText et21 = (EditText) findViewById(R.id.et21);
        final EditText et22 = (EditText) findViewById(R.id.et22);
        final Button b21 = (Button) findViewById(R.id.b21);
        final TextView registerLink = (TextView) findViewById(R.id.tv21);


        registerLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent registerIntent = new Intent(loginActivity.this, RegisterActivity.class);
                loginActivity.this.startActivity(registerIntent);


            }


        });


        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email_ID = et21.getText().toString();
                final String Password = et22.getText().toString();

                progressBar=new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("loading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressBarStatus=0;



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String emailID = jsonResponse.getString("Email_ID");
                                String password = jsonResponse.getString("Password");
                                Intent intent = new Intent(loginActivity.this,surveyActivity.class);
                                intent.putExtra("Password", Password);
                                intent.putExtra("Email_ID", Email_ID);

                                loginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                         }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(Email_ID, Password, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HC", String.valueOf(error));
                    }
                });
                Log.e("HC", "Sending request");
                RequestQueue queue = Volley.newRequestQueue(loginActivity.this);

                queue.add(loginRequest);
            }

        });

    }
}
