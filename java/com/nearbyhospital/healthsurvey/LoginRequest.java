package com.nearbyhospital.healthsurvey;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL="https://explicit-debits.000webhostapp.com/Login2.php";
    private Map<String,String> params;


    public LoginRequest( String Email_ID, String Password,
                         Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("Email_ID",Email_ID);
        params.put("Password",Password);

    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}
