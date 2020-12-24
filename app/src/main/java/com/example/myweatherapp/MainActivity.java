package com.example.myweatherapp;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

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

        renderWeatherData("Chennai");

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(Weather weather) {



            super.onPostExecute(weather);

            DecimalFormat decimalFormat= new DecimalFormat("#.#");
            String tempformat=decimalFormat.format(weather.currentCondition.getTemperature());

            DateFormat df= DateFormat.getTimeInstance();

            String sunraisedate = df.format(new Date(weather.place.getSunrise()));
            String sunsetdate=df.format(new Date(weather.place.getSunset()));
            String lastupdatedate=df.format(new Date(weather.place.getLastupdate()));
            city.setText(weather.place.getCity());
            clouds.setText("Clouds :"+weather.clouds.getPrecipitation()+"%");
            temp.setText(tempformat+"°C");
            humidity.setText("Humidity :"+weather.currentCondition.getHumidity()+"%");
            pressure.setText("Pressure :"+weather.currentCondition.getPressure()+"Hpa");
            wind.setText("Wind :"+weather.wind.getSpeed()+ "mps");
            sunrise.setText("Sun Raise :"+sunraisedate);
            sunset.setText("Sun Set :"+sunsetdate);
            Udpate.setText("Last Updated on :"+lastupdatedate);



        }
    }}
