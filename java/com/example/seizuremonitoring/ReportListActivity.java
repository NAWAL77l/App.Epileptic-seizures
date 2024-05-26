package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import adapter.RecyclerViewAdapterNotification;
import adapter.RecyclerViewAdapterReportList;

public class ReportListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewReport;
    private RecyclerViewAdapterReportList adapterReportList;
    private String  reportList []={"Name 1","Name 2","Name 3","Name 4"
            ,"Name 5", "Name 6"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        recyclerViewReport=findViewById(R.id.recyclerViewReport);
        recyclerViewReport.setLayoutManager(new LinearLayoutManager(this));
        adapterReportList =new RecyclerViewAdapterReportList(this,reportList);
        recyclerViewReport.setAdapter(adapterReportList);
    }
}