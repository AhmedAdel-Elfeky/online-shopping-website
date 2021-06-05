package com.iti.onlinestore;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Order extends AsyncTask<String,Void,String> {
    public TextView id;
    public TextView state;
    public TextView date;
    public TextView expectedDate;
    public Order()
    {}

    public Order(TextView o_id, TextView oState,TextView oDate, TextView oExpected)
    {
        id = o_id;
        state = oState;
        date = oDate;
        expectedDate = oExpected;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Check for successful response code or throw error
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }


            // Buffer the result into a string
            BufferedReader buffrd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = buffrd.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            Log.i("-------",result);

            buffrd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            Gson gson = new Gson();
            OrderInfoModel response = gson.fromJson(s, OrderInfoModel.class);
//            Log.i("+++++++++",response.());
            id.setText(response.getOrderId());
            state.setText(response.getState());
            date.setText(response.getOrderDate());
            expectedDate.setText(response.getExpectedDate());

        }
        else {
            Log.i("+++++++++","dddddddddddddddddddddddddddddddddddddddddddd");
        }
    }
}
