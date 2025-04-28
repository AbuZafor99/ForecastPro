package com.example.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import android.content.pm.ResolveInfo;

public class Windy_WebView extends AppCompatActivity {

    private TextView homeB, seemoreB, webviewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windy_web_view);

        // Initialize navigation buttons
        homeB = findViewById(R.id.homeButton2);
        seemoreB = findViewById(R.id.smoreButton2);
        webviewB = findViewById(R.id.webviewButton2);

        // Set click listeners
        homeB.setOnClickListener(v -> navigateTo(MainActivity.class));
        seemoreB.setOnClickListener(v -> navigateTo(See_More.class));
        webviewB.setOnClickListener(v -> navigateTo(Windy_WebView.class));

        // Window insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void navigateTo(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    // Social media handlers
    public void openGithub(View view) {
        openUrlInBrowser("https://github.com/AbuZafor99");
    }

    public void openLinkedIn(View view) {
        openUrlInBrowser("https://linkedin.com/in/yourprofile");
    }

    public void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:zaforsadiq987l@gmail.com"));
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Toast.makeText(this, "No email app found to send the email.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openUrlInBrowser(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Check available apps
            List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);
            if (resolveInfoList.isEmpty()) {
                Toast.makeText(this, "No browser app found to open the link.", Toast.LENGTH_SHORT).show();
                System.out.println("No apps available to handle the intent.");
            } else {
                System.out.println("Available apps to handle the intent:");
                for (ResolveInfo resolveInfo : resolveInfoList) {
                    System.out.println("App: " + resolveInfo.activityInfo.packageName);
                }
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "An error occurred while opening the link.", Toast.LENGTH_SHORT).show();
            System.out.println("Error: " + e.getMessage());
        }
    }
}