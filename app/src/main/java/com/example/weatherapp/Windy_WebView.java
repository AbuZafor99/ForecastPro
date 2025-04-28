package com.example.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Windy_WebView extends AppCompatActivity {


    private TextView homeB, seemoreB, webviewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_windy_web_view);

        // Initialize navigation buttons
        homeB = findViewById(R.id.homeButton2);
        seemoreB = findViewById(R.id.smoreButton2);
        webviewB = findViewById(R.id.webviewButton2);


        homeB.setOnClickListener(v -> navigateTo(MainActivity.class));
        seemoreB.setOnClickListener(v -> navigateTo(See_More.class));
        webviewB.setOnClickListener(v -> navigateTo(Windy_WebView.class));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(Windy_WebView.this, cls);
        startActivity(intent);
    }


    public void openGithub(View view) {
        openWebUrl("https://github.com/abuZafor99");
    }

    public void openLinkedIn(View view) {
        openWebUrl("https://linkedin.com/in/yourprofile");
    }

    public void openTwitter(View view) {
        openWebUrl("https://twitter.com/yourhandle");
    }

    public void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:zaforsadiq987l@gmail.com"));
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }

    private void openWebUrl(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}