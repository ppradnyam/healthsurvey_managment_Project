package com.nearbyhospital.healthsurvey;


import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.util.Log;
        import android. view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.EditText;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        final EditText et5 = (EditText) findViewById(R.id.et5);
        final Button b1 = (Button) findViewById(R.id.b1);



        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("HC", "Response received");
                    Log.e("HC", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                        RegisterActivity.this.startActivity(intent);

                    } else {
                         AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Register Failed")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        b1.setOnClickListener(new View.OnClickListener() {
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

                final String Email_ID = et1.getText().toString();
                final String Council_name = et2.getText().toString();
                final String Council_address = et3.getText().toString();
                final String Council_contactno = et4.getText().toString();
                final String Password = et5.getText().toString();

                RegisterRequest registerRequest = new RegisterRequest(Email_ID ,Council_name ,Council_address ,Council_contactno ,Password, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HC", String.valueOf(error));
                    }
                });
                Log.e("HC", "Sending request");
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

                queue.add(registerRequest);
            }
        });
    }
}