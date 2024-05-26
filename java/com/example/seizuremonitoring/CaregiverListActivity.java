package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.VolleySingleton;
import Model.Caregiver;
import Server.URLs;
import adapter.RecyclerViewAdapterCaregiverList;
import adapter.RecyclerViewAdapterNotification;

public class CaregiverListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewcaregiver;
    private RecyclerViewAdapterCaregiverList adapterCaregiverList;
    private ArrayList<Caregiver> caregiverList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_list);
        caregiverList= new ArrayList<>();
        recyclerViewcaregiver=findViewById(R.id.recyclerViewCaregiver);
        recyclerViewcaregiver.setLayoutManager(new LinearLayoutManager(this));
        adapterCaregiverList =new RecyclerViewAdapterCaregiverList(this,caregiverList);
        recyclerViewcaregiver.setAdapter(adapterCaregiverList);
        getData();
    }
    public List<Caregiver> getData(){


       caregiverList.clear();
        final ProgressDialog progressDialog = new ProgressDialog(CaregiverListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest jsonObjectRequest = new  StringRequest( Request.Method.GET,
                URLs.URL_GET_ALL_CAREGIVER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);

                    Log.d(CaregiverListActivity.class.getName(),  "onResponse: "+response);
                    JSONArray caregiverArray = object.getJSONArray("data");

                    if (object.getInt("status")!=-1){
                        for (int i = 0; i <caregiverArray.length(); i++) {
                            JSONObject caregiverObj = caregiverArray.getJSONObject(i);
                           Caregiver caregiver = new Caregiver();
                           caregiver.setId(caregiverObj.getInt("id"));
                           caregiver.setFullname(caregiverObj.getString("fullname"));
                           caregiver.setUsername(caregiverObj.getString("username"));
                           caregiver.setEmail(caregiverObj.getString("email"));
                           caregiver.setPhone(caregiverObj.getString("phone"));
                            caregiverList.add(caregiver);
                            adapterCaregiverList.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }




                    }} catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CaregiverListActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        } )
        {
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("Accept","application/json");
                return  params;

            }
        }

                ;

        VolleySingleton.getInstance(CaregiverListActivity.this).addToRequestQueue(jsonObjectRequest);
        return caregiverList;
    }
}