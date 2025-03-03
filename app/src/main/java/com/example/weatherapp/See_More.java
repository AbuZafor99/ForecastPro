package com.example.weatherapp;

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

public class See_More extends AppCompatActivity {
    private TextView homeB, seemoreB, webviewB;
    WebView tvMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_see_more);

        homeB=findViewById(R.id.homButton) ;
        seemoreB=findViewById(R.id.seeButton) ;
        webviewB=findViewById(R.id.webButton) ;
        tvMore=findViewById(R.id.tvMore) ;


        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(See_More.this, MainActivity.class) ;
                startActivity(myIntent);
            }
        });

        seemoreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(See_More.this, See_More.class) ;
                startActivity(myIntent);
            }
        });

        webviewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(See_More.this, Windy_WebView.class) ;
                startActivity(myIntent);
            }
        });

        tvMore.getSettings().setJavaScriptEnabled(true);
        tvMore.loadUrl("https://www.windy.com/-Waves-waves?waves,23.727,90.409,5");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}