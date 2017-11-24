package com.nearbyhospital.healthsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class hosplistRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="https://explicit-debits.000webhostapp.com/hosp.php";
    private Map<String,String> params;


    public hosplistRequest(String area_pincode,
                         Response.Listener<String> listener, Response.ErrorListener errorListener){



        super(Method.POST,REGISTER_REQUEST_URL,listener,errorListener);
        params = new HashMap<>();
        params.put("area_pincode",area_pincode);


    }

    @Override

    public Map<String,String> getParams(){
        return params;
    }
}

