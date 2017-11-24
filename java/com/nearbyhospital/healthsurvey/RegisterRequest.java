package com.nearbyhospital.healthsurvey;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://explicit-debits.000webhostapp.com/register2.php";
    private Map<String,String> params;


    public RegisterRequest(String Email_ID, String Council_name, String Council_address, String Council_contactno, String Password,
                           Response.Listener<String> listener, Response.ErrorListener errorListener){



        super(Method.POST,REGISTER_REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("Email_ID",Email_ID);
        params.put("Council_name",Council_name);
        params.put("Council_address",Council_address);
        params.put("Council_contactno",Council_contactno);
        params.put("Password",Password);


    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}

