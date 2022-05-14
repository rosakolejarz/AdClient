package com.example.adclient;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AsyncNetwork;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue = null;
    private String URL = "http://192.168.1.200:9000/api/v1/ad/user/1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        requestQueue = Volley.newRequestQueue(this); // Creates a default instance of the worker pool and calls RequestQueue.start() on it.

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(new Sender(imageView, requestQueue, URL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("..............on Resume-------------------");
    }

}