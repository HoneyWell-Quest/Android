package com.example.rehanr.honeywelluser.ServerUtils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rehan r on 27-09-2016.
 */
public class HttpClientWrapper {

    OkHttpClient client;
    public HttpClientWrapper(){
        client = new OkHttpClient();
    }

    public String doUnAuthPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        Log.e("--> POST <--",url + " :: " + json + " :: " + responseString);
        return responseString;
    }

}
