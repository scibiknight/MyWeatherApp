package com.example.myweatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText usercity;
    private FloatingActionButton location;
    private TextView Udpate,wind,humidity,sunrise,sunset,clouds,pressure,temp,city;
    private ImageView img;

    Weather weather =new Weather();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab =(FloatingActionButton) findViewById(R.id.fab);
        location=(FloatingActionButton) findViewById(R.id.location);

        img=(ImageView)findViewById(R.id.img);
        usercity=(EditText) findViewById(R.id.usercity);

        city=(TextView)findViewById(R.id.city);
        wind=(TextView)findViewById(R.id.wind);
        clouds=(TextView)findViewById(R.id.clouds);
        humidity=(TextView)findViewById(R.id.Humidity);
        sunrise=(TextView)findViewById(R.id.Sunrise);
        sunset=(TextView)findViewById(R.id.Sunset);
        Udpate=(TextView)findViewById(R.id.update);
        temp=(TextView)findViewById(R.id.temp);
        pressure=(TextView)findViewById(R.id.Pressure);

        renderWeatherData("Tirunelveli");

    }

    public void renderWeatherData(String city){
        WeatherTask weatherTask=new WeatherTask();
        weatherTask.execute(new String[]{city+"&units=metric"});


    }

    private class WeatherTask extends AsyncTask<String,Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            weather = JSONWeatherParser.getWeather(data);
            Log.v("Data: ", weather.currentCondition.getCondition());
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {


            super.onPostExecute(weather);

            if (weather != null) {
                // Do you work here on success
            } else {



            }
        }
    }}
