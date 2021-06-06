 package com.iti.onlinestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

 public class MainActivity extends AppCompatActivity {
     Button getInfo;
     EditText orderId;

     TextView orderState;
     TextView o_id;
     TextView date;
     TextView expectedDate;

     Order o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInfo = findViewById(R.id.button);
        orderId = findViewById(R.id.editTextNumber);
        orderState = findViewById(R.id.order_state);
        o_id = findViewById(R.id.order_id);
        date= findViewById(R.id.date);
        expectedDate = findViewById(R.id.expected_date);
        o  = new Order(o_id,orderState,date,expectedDate);



        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oId = orderId.getText().toString();
//                Toast.makeText(getApplicationContext(),g,Toast.LENGTH_LONG).show();
                o.execute("http://10.0.0.2:8080/order-api/rest/DML/get/"+oId);
            }
        });


    }




 }