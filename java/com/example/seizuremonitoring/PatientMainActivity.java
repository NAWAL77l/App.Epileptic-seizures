package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientMainActivity extends AppCompatActivity {
ConstraintLayout notificationLayoutP,profielLayout,cargiverListLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        notificationLayoutP=findViewById(R.id.notificationLayoutP);
        profielLayout=findViewById(R.id.profielLayout);
        cargiverListLayout=findViewById(R.id.cargiverListLayout);
        notificationLayoutP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMainActivity.this, NotificatonActivity.class);
                startActivity(intent);
            }
        });
        profielLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMainActivity.this,ProfielActivity.class);
                startActivity(intent);
            }
        });
        cargiverListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientMainActivity.this,CaregiverListActivity.class);
                startActivity(intent);
            }
        });
    }
}