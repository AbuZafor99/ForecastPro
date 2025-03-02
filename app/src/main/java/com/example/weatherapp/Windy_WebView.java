package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Windy_WebView extends AppCompatActivity {
    WebView webTvDisplay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_windy_web_view);

        webTvDisplay=findViewById(R.id.webView1) ;
        webTvDisplay.getSettings().setJavaScriptEnabled(true);
        webTvDisplay.loadUrl("https://talimeislam.com/%E0%A6%AE%E0%A6%BE%E0%A6%B8%E0%A6%A8%E0%A7%82%E0%A6%A8-%E0%A6%A6%E0%A7%8B%E0%A7%9F%E0%A6%BE/");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}