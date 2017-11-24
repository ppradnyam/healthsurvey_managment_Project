package com.nearbyhospital.healthsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class getpdataRequest extends StringRequest {

    private static final String REQUEST_URL="https://explicit-debits.000webhostapp.com/hosp3.php";
    private Map<String,String> params;


    public getpdataRequest(String ID,
                           Response.Listener<String> listener, Response.ErrorListener errorListener){



        super(Method.POST,REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("ID",ID);


    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}

