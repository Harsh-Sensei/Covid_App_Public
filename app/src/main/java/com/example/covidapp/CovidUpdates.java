package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.* ;
import java.net.* ;
import java.util.*;

public class CovidUpdates extends AppCompatActivity{
    public Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_updates_india);

        System.out.println("htg_sensei");

        try
        {
            JSONObject root = new JSONObject(fetch_json_string());


        }
        catch (Exception e){
            e.printStackTrace();
        }



        TextView tv1 = (TextView)findViewById(R.id.total_cases_id);
        tv1.setText(map.get("\"cases\""));

        TextView tv2 = (TextView)findViewById(R.id.today_cases_id);
        tv2.setText(map.get("\"todayCases\""));



    }

    public String fetch_json_string() {
        final String[] json_string = {null};


        Thread thread;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL state_url = new URL("https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true");
                    URLConnection con = state_url.openConnection();

                    InputStream state_stream = con.getInputStream();

                    BufferedReader state_buffer = new BufferedReader(new InputStreamReader(state_stream));

                    String inputLine = null;

                    StringBuffer response = new StringBuffer();
                    while ((inputLine = state_buffer.readLine()) != null) {
                        response.append(inputLine);
                    }

                    state_buffer.close();

                    json_string[0] = response.toString();

                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        try {
            thread.join();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Printing jsonstring");
        System.out.println(json_string[0]);
        return json_string[0];
    }

}