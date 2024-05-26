package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CaregiverMainActivity extends AppCompatActivity {

    ConstraintLayout notificationLayout,profielLayotC,adding_assest3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_main);
        notificationLayout=findViewById(R.id.notificationLayout);
        profielLayotC=findViewById(R.id.profielLayotC);
        adding_assest3=findViewById(R.id.adding_assest3);

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverMainActivity.this,NotificatonActivity.class);
                startActivity(intent);
            }
        });
        profielLayotC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverMainActivity.this,CaregiverProfielActivity.class);
                startActivity(intent);
            }
        });
        adding_assest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverMainActivity.this,ReportListActivity.class);
                startActivity(intent);
            }
        });
    }
}