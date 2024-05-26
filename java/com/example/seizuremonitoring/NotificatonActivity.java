package com.example.seizuremonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import adapter.RecyclerViewAdapterNotification;

public class NotificatonActivity extends AppCompatActivity {
    private RecyclerView recyclerViewnotification;
    private RecyclerViewAdapterNotification adapterNotification;
    private String  notificationList []={"Notification 1","Notification 2","Notification 3","Notification 4"
    ,"Notification 5", "Notification 6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaton);
        recyclerViewnotification=findViewById(R.id.recyclerViewNotification);
        recyclerViewnotification.setLayoutManager(new LinearLayoutManager(this));
        adapterNotification =new RecyclerViewAdapterNotification(this,notificationList);
        recyclerViewnotification.setAdapter(adapterNotification);
    }
}