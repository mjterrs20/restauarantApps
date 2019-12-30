package com.example.aditia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.aditia.auth.LoginActivity;
import com.example.aditia.model.Login;

public class SplashScreenActivity extends AppCompatActivity {
    protected int _splashTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,
                        LoginActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
