package com.example.rehanr.honeywelluser.Services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.rehanr.honeywelluser.ServerUtils.ServerRequest;
import com.example.rehanr.honeywelluser.ServerUtils.Urls;

import org.json.JSONObject;

/**
 * Created by rehan r on 27-09-2016.
 */
public class MyService extends Service {

    //Address address;
    private final static int INTERVAL = 1000 * 60 * 2; //2 minutes
    Handler mHandler = new Handler();

    String accessPointMac = "";
    String ssid ="";
    String deviceMac= "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","started");
        check();
        return START_STICKY;
    }

    private void check() {

        CountDownTimer countDownTimer = new CountDownTimer(600000,1000) {
            Address address = new Address(getApplicationContext());
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                boolean bool = NetworkUtils.isConnected(MyService.this);
                if (bool){
                        if (address.getAccessPointMacAddress() != null) {
                            accessPointMac = address.getAccessPointMacAddress().replaceAll(":","").toUpperCase();
                        }
                        if (address.getCurrentDeviceMacAddress() != null) {
                            deviceMac = address.getCurrentDeviceMacAddress().replace(":","").toUpperCase();
                        }
                        Log.e("Access Point MAC", accessPointMac);
                        Log.e("Device MAC", deviceMac);
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadToServer(deviceMac, accessPointMac);
                                mHandler.postDelayed(this, INTERVAL);
                            }
                        });
                        thread.start();
                    } else {
                        Log.e("status","Not Connected");
                }
                check();
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        Log.e("service","closed");
    }


    private void uploadToServer(String deviceMac, String accessPointMac) {
        JSONObject jsonObject = new ServerRequest().doPostRequest(deviceMac,accessPointMac);
    }

}
