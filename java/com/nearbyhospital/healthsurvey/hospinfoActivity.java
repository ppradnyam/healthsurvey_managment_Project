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


public class hospinfoActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus =0;
    private Handler progressBarHandler=new Handler();
    private long fileSize =0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospinfo);

        final EditText et71 = (EditText) findViewById(R.id.et71);
        final Button b71 = (Button) findViewById(R.id.b71);



        b71.setOnClickListener(new View.OnClickListener() {
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

                final String ID= et71.getText().toString();
                final ArrayList<String> caseList = new ArrayList<String>();
                final ArrayList<String> virusList = new ArrayList<String>();

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HC", "Response:"+response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int length = jsonResponse.getInt("length");

                            if(success) {
                                for (int i = 0; i < length; i++) {
                                    JSONObject caseData = (JSONObject) jsonResponse.get(String.valueOf(i));
                                    String virus = caseData.getString("virus");
                                    String cases = caseData.getString("no_of_cases");
                                    virusList.add(virus);
                                    caseList.add(cases);
                                }
                                Intent pdataActivity = new Intent(hospinfoActivity.this, pdataActivity.class);
                                Bundle b = new Bundle();
                                b.putStringArrayList("caseList", caseList);
                                b.putStringArrayList("virusList", virusList);
                                pdataActivity.putExtras(b);
                                startActivity(pdataActivity);
                            }

                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(hospinfoActivity.this);
                                builder.setMessage("No such data exists for this ID!!")
                                        .setNegativeButton("Retry with different ID", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                hospinfoRequest hrequest = new hospinfoRequest(ID, responseListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HC", String.valueOf(error));
                    }
                });
                Log.e("HC", "Sending request");
                RequestQueue queue = Volley.newRequestQueue(hospinfoActivity.this);

                queue.add(hrequest);
            }

        });
    }
}







