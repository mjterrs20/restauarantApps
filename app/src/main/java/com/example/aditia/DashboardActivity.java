package com.example.aditia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aditia.data.Session;

public class DashboardActivity extends AppCompatActivity {
    Button logOut;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new Session(this);
        initBinding();
        initButton();
    }
    private void initBinding (){
        logOut = findViewById(R.id.btn_logout);
    }
    private void initButton (){
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });
    }
}

