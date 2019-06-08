package com.fibredariane.horoscope.chinois.biorythmeETRES.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Activities.MainActivity;

public class SplashActivity extends AppCompatActivity    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
