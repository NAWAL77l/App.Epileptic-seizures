package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Controller.SessionManagerPatient;
import Controller.VolleySingleton;
import Server.URLs;

public class ProfielActivity extends AppCompatActivity {

    private TextView username,fullname,email,phone,address,age;
    private Button logout,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiel);
        username=findViewById(R.id.textView5);
        fullname=findViewById(R.id.textView6);
        email=findViewById(R.id.textView7);
        phone=findViewById(R.id.textView8);
        address=findViewById(R.id.textView9);
        age=findViewById(R.id.textView10);
        logout=findViewById(R.id.logout);
        edit=findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfielActivity.this, PatientEditProfielActivity.class));
            }
        });

        if (!SessionManagerPatient.getInstance(ProfielActivity.this).isLoggedIn()) {

            startActivity(new Intent(ProfielActivity.this, LoginActivity.class));
        }

        final int id =SessionManagerPatient.getInstance(ProfielActivity.this).getId().getId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_PATIENT_PROFIEL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status")!="-1"){

                        JSONObject customerJson = obj.getJSONObject("data");



                        username.setText(customerJson.getString("username"));
                        email.setText(customerJson.getString("email"));
                        phone.setText(customerJson.getString("phone"));
                        fullname.setText(customerJson.getString("fullname"));
                        address.setText(customerJson.getString("address"));
                        age.setText(customerJson.getString("age"));

                    } else {
                        Toast.makeText(ProfielActivity.this,
                                "SomeThing is Wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfielActivity.this,
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                // params.put("Accept-Encoding", "application/json");
                params.put("id",String.valueOf(id));
                return  params;

            }
        }   ;


        VolleySingleton.getInstance(ProfielActivity.this).addToRequestQueue(stringRequest);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagerPatient.getInstance(ProfielActivity.this).User_Logout();
            }
        });



    }
}