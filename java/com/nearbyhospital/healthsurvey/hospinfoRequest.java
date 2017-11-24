package com.nearbyhospital.healthsurvey;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class hospinfoRequest extends StringRequest {

    private static final String Search_URL="https://explicit-debits.000webhostapp.com/hosp2.php";
    private Map<String,String> params;


    public hospinfoRequest(String ID,
                         Response.Listener<String> listener, Response.ErrorListener errorListener){



        super(Method.POST,Search_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("ID",ID);


    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}

