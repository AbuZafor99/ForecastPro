package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Windy_WebView extends AppCompatActivity {
    WebView webTvDisplay;
    private TextView homeB, seemoreB, webviewB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_windy_web_view);

        webTvDisplay=findViewById(R.id.webView1) ;
        homeB=findViewById(R.id.homeButton2) ;
        seemoreB=findViewById(R.id.smoreButton2) ;
        webviewB=findViewById(R.id.webviewButton2) ;


        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(Windy_WebView.this, MainActivity.class) ;
                startActivity(myIntent);
            }
        });

        seemoreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(Windy_WebView.this, See_More.class) ;
                startActivity(myIntent);
            }
        });

        webviewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(Windy_WebView.this, Windy_WebView.class) ;
                startActivity(myIntent);
            }
        });
        webTvDisplay.getSettings().setJavaScriptEnabled(true);
        webTvDisplay.loadUrl("https://www.windy.com/-Waves-waves?waves,23.727,90.409,5");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}