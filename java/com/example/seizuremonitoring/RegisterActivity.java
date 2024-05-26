package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {

     private TextView loginText;
 private   Button register;
 private EditText username,password,fullname,email,phone,address,age;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginText=findViewById(R.id.loginText);
        register=findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);



        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerPatient();
            }
        });

    }
    public  void registerPatient(){


        final String myFullname=fullname.getText().toString().trim();
        final String myUsername=username.getText().toString().trim();
        final String myPhone=phone.getText().toString().trim();
        final String myEmail=email.getText().toString().trim();
        final String myPassword=password.getText().toString().trim();
        final String myAge = age.getText().toString().trim();
        final String myAddress = address.getText().toString().trim();

        if (TextUtils.isEmpty(myFullname)){
            fullname.setError("Please Enter Your Fullname");
            fullname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myUsername)){
            username.setError("Please Enter Your Username");
            username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(myAddress)){
            address.setError("Please Enter Your Address");
            address.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(myPhone)){
            phone.setError("Please Enter Your Phone");
            phone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(myEmail)){
            email.setError("Please Enter Your Email");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(myAge)){
          age.setError("Please Enter Your Age");
            age.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(myPassword)){
            password.setError("Please Enter Your Password");
            password.requestFocus();
            return;
        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_REGISTER_PATIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);

                    Log.d(RegisterActivity.class.getName(), "onResponse: "+response);
                    if (object.getString("status")!="-1"){

                        Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();

                        JSONObject patientJson = object.getJSONObject("data");
                        Patient patient = new Patient(
                                patientJson.getInt("id")
                        );
                        SessionManagerPatient.getInstance(RegisterActivity.this).userLogin(patient);

                        startActivity(new Intent(RegisterActivity.this,PatientMainActivity.class));

                    } else {
                        Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        })

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params =new HashMap<>();
                params.put("Accept-Encoding","application/json");
                params.put("username",myUsername);
                params.put("phone",myPhone);
                params.put("password",myPassword);
                params.put("email",myEmail);
                params.put("address",myAddress);
                params.put("age",myAge);
                params.put("fullname",myFullname);
                return params;
            }

        }



                ;

        VolleySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);


    }
}