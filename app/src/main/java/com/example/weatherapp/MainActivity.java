package com.example.weatherapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView cityTextView, tempTextView, conditionTextView, windTextView, humidityTextView, rainTextView, highLowTextView, dateTextView;
    private ImageView weatherImageView;
    private SearchView searchView;
    private RequestQueue queue ;
    private TextView homeB, seemoreB, webviewB;
    private String apiKey = "ad9c0d412f333d90765cd7a329d6e3b4"; // Your API key

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.textView8);
        tempTextView = findViewById(R.id.textView4);
        conditionTextView = findViewById(R.id.textView3);
        windTextView = findViewById(R.id.windtextView6);
        humidityTextView = findViewById(R.id.humiditytextView6);
        rainTextView = findViewById(R.id.raintextView6);
        highLowTextView = findViewById(R.id.textView5);
        dateTextView = findViewById(R.id.textView2);
        searchView = findViewById(R.id.search_bar);
        weatherImageView = findViewById(R.id.imageView);
        homeB=findViewById(R.id.homButton) ;
        seemoreB=findViewById(R.id.seeButton) ;
        webviewB=findViewById(R.id.webButton) ;


        queue = Volley.newRequestQueue(this);
        updateDate();
        fetchWeatherData("Dhaka");

        //====================weather search action==========================
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchWeatherData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //=========================on button click action====================


//        homeB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        seemoreB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        webviewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, Windy_WebView.class) ;
                startActivity(myIntent);
            }
        });
    }

    private void fetchWeatherData(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            double temperature = main.getDouble("temp");
                            int humidity = main.getInt("humidity");
                            double tempMax = main.getDouble("temp_max");
                            double tempMin = main.getDouble("temp_min");

                            JSONObject wind = response.getJSONObject("wind");
                            double windSpeed = wind.getDouble("speed");

                            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                            String condition = weather.getString("description");
                            int weatherId = weather.getInt("id");

                            String rainPercentage = "0%";
                            if (response.has("rain")) {
                                JSONObject rain = response.getJSONObject("rain");
                                if (rain.has("1h")) {
                                    double rainValue = rain.getDouble("1h");
                                    rainPercentage = rainValue + "mm";
                                }
                            }

                            String cityName = response.getString("name");
                            String countryCode = response.getJSONObject("sys").getString("country");

                            cityTextView.setText(cityName + ", " + countryCode);
                            tempTextView.setText(temperature + "°C");
                            conditionTextView.setText(condition);
                            windTextView.setText(windSpeed + " km/h");
                            humidityTextView.setText(humidity + "%");
                            rainTextView.setText(rainPercentage);
                            highLowTextView.setText("H: " + tempMax + "° L: " + tempMin + "°");

                            setWeatherIcon(weatherId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cityTextView.setText("City Not Found!");
                        tempTextView.setText("--°C");
                        conditionTextView.setText("--");
                        windTextView.setText("-- km/h");
                        humidityTextView.setText("--%");
                        rainTextView.setText("--");
                        highLowTextView.setText("H: --° L: --°");
                        error.printStackTrace();
                    }
                });

        queue.add(request);
    }

    private void setWeatherIcon(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            weatherImageView.setImageResource(R.drawable.storm);
        } else if (weatherId >= 300 && weatherId <= 321) {
            weatherImageView.setImageResource(R.drawable.cloudy_sunny);
        } else if (weatherId >= 500 && weatherId <= 531) {
            weatherImageView.setImageResource(R.drawable.rainy);
        } else if (weatherId >= 600 && weatherId <= 622) {
            weatherImageView.setImageResource(R.drawable.snowy);
        } else if (weatherId >= 701 && weatherId <= 781) {
            weatherImageView.setImageResource(R.drawable.cloudy_3);
        } else if (weatherId == 800) {
            weatherImageView.setImageResource(R.drawable.sun);
        } else if (weatherId >= 801 && weatherId <= 804) {
            weatherImageView.setImageResource(R.drawable.cloudy_sunny);
        } else {
            weatherImageView.setImageResource(R.drawable.umbrella);
        }
    }

    private void updateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        dateTextView.setText(currentDate);
    }
}