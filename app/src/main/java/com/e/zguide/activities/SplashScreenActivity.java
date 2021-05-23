package com.e.zguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.e.zguide.R;
import com.e.zguide.repositories.SQLDataBaseRepository;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        SQLDataBaseRepository.getInstance(this);

        boolean isVisited = SharedPreferencesRepository.getInstance(this).getIsVisited();

        Intent intent;

        if (isVisited) {
            intent = new Intent(this, RootActivity.class);
        } else {
            intent = new Intent(this, OnboardingActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new Handler().postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 2000);
    }
}