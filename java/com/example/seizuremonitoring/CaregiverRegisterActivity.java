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

import Controller.SessionManagerCaregiver;
import Controller.SessionManagerPatient;
import Controller.VolleySingleton;
import Model.Caregiver;
import Model.Patient;
import Server.URLs;

public class CaregiverRegisterActivity extends AppCompatActivity {
    private TextView loginText;
    private Button register;
    private EditText username,password,fullname,email,phone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_register);
        loginText=findViewById(R.id.loginText);
        register=findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverRegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCaregiver();
            }
        });
    }
    public  void registerCaregiver(){


        final String myFullname=fullname.getText().toString().trim();
        final String myUsername=username.getText().toString().trim();
        final String myPhone=phone.getText().toString().trim();
        final String myEmail=email.getText().toString().trim();
        final String myPassword=password.getText().toString().trim();

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


        if (TextUtils.isEmpty(myPassword)){
            password.setError("Please Enter Your Password");
            password.requestFocus();
            return;
        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_REGISTER_CAREGIVER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);

                    Log.d(RegisterActivity.class.getName(), "onResponse: "+response);
                    if (object.getString("status")!="-1"){



                        JSONObject caregiverJson = object.getJSONObject("data");
                       Caregiver caregiver = new Caregiver(
                                caregiverJson.getInt("id")
                        );
                        SessionManagerCaregiver.getInstance(CaregiverRegisterActivity.this).userLogin(caregiver);
                        Toast.makeText(CaregiverRegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CaregiverRegisterActivity.this,CaregiverMainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(CaregiverRegisterActivity.this,"Register Failed",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CaregiverRegisterActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
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
                 params.put("fullname",myFullname);
                return params;
            }

        }



                ;

        VolleySingleton.getInstance(CaregiverRegisterActivity.this).addToRequestQueue(stringRequest);


    }
}