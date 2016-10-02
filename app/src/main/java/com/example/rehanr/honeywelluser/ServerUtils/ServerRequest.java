package com.example.rehanr.honeywelluser.ServerUtils;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by rehan r on 27-09-2016.
 */
public class ServerRequest {

    HttpClientWrapper clientWrapper;
    public ServerRequest(){
        clientWrapper = new HttpClientWrapper();
    }

    public JSONObject doPostRequest(String deviceMac, String accessPintMac){
        JSONObject request = new JSONObject();
        JSONObject responseObject = null;
        try{
            request.put("mac",deviceMac);
            request.put("rmac",accessPintMac);
            String response = clientWrapper.doUnAuthPostRequest(Urls.BASE_URL+Urls.UPLOAD_ADDRESS,request.toString());
            responseObject = new JSONObject(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  responseObject;
    }

}
