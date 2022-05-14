package com.example.adclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;


public class Sender implements Runnable {

    private static final String TAG = "SENDER";
    private static String URL = null;
    private final ImageView imageView;
    private RequestQueue requestQueue;
    private Response.ErrorListener errorListener = null;
    private Response.Listener<android.graphics.Bitmap> responseListener = null;

    public Sender(ImageView imageView, RequestQueue requestQueue, String URL) {
        this.requestQueue = requestQueue;
        this.imageView = imageView;
        this.URL = URL;
    }

    public void run() {

        responseListener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                System.out.println("onResponse: \n\n\n" + response);
                imageView.setImageBitmap((Bitmap) response);
            }
        };

        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("onErrorResponse: \n\n\n" + error);
            }
        };

        while (true){
                    Log.d(TAG, "---SenderThread----: " + Thread.currentThread() + "is sending to url " + URL);
            ImageRequest imageRequest = new ImageRequest(URL, responseListener, imageView.getWidth(),
                    imageView.getHeight(), ImageView.ScaleType.FIT_CENTER, Bitmap.Config.valueOf("ALPHA_8"), errorListener);
            requestQueue.add(imageRequest);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
