package com.example.rehanr.honeywelluser;

import android.content.Intent;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rehanr.honeywelluser.Services.MyService;
import com.example.rehanr.honeywelluser.Services.NetworkUtils;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, MyService.class));
    }

}
