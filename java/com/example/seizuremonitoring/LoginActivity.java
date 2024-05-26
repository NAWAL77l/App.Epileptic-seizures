package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import Model.Patient;
import Server.URLs;

public class LoginActivity extends AppCompatActivity {

   private TextView regText;
    private  Button login;
    private EditText email,password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regText=findViewById(R.id.regText);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);


        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientLogin();
            }
        });
    }
    private void patientLogin(){
        final String myEmail = email.getText().toString().trim();
        final String myPassword = password.getText().toString().trim();

        if(TextUtils.isEmpty(myEmail)){
            email.setError("Enter you email please");
            email.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(myPassword)){
            password.setError("Enter you password please");
            password.requestFocus();
            return;
        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_LOGIN_PATIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status")!="-1"){

                        Toast.makeText(LoginActivity.this,
                                "Welcome", Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("data");

                        Patient patient = new Patient(userJson.getInt("id"));

                        SessionManagerPatient.getInstance(LoginActivity.this).userLogin(patient);

                        Intent intent = new Intent(LoginActivity.this,PatientMainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this,
                                obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("email", myEmail);
                params.put("password", myPassword);
                return  params;

            }
        }   ;


        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);


    }
}