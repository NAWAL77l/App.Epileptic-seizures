package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import Controller.SessionManagerCaregiver;
import Controller.SessionManagerPatient;
import Controller.VolleySingleton;
import Server.URLs;

public class CaregiverProfielActivity extends AppCompatActivity {
    private TextView username,fullname,email,phone;
    private Button logout,edit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_profiel);
        username=findViewById(R.id.textView5);
        fullname=findViewById(R.id.textView6);
        email=findViewById(R.id.textView7);
        phone=findViewById(R.id.textView8);
        logout=findViewById(R.id.logout);
        edit=findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaregiverProfielActivity.this, CaregiverEditProfielActivity.class));
            }
        });

        if (!SessionManagerCaregiver.getInstance(CaregiverProfielActivity.this).isLoggedIn()) {

            startActivity(new Intent(CaregiverProfielActivity.this, LoginActivity.class));
        }

        final int id =SessionManagerCaregiver.getInstance(CaregiverProfielActivity.this).getId().getId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_CAREGIVER_PROFIEL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("jjjjjjjjjjjjjjjj",String.valueOf(id));

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status")!="-1"){

                        JSONObject customerJson = obj.getJSONObject("data");

                        username.setText(customerJson.getString("username"));
                        email.setText(customerJson.getString("email"));
                        phone.setText(customerJson.getString("phone"));
                        fullname.setText(customerJson.getString("fullname"));


                    } else {
                        Toast.makeText(CaregiverProfielActivity.this,
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
                        Toast.makeText(CaregiverProfielActivity.this,
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


        VolleySingleton.getInstance(CaregiverProfielActivity.this).addToRequestQueue(stringRequest);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagerCaregiver.getInstance(CaregiverProfielActivity.this).User_Logout();
            }
        });


    }
}