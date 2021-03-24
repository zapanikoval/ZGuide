package com.e.zguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import com.e.zguide.activities.OnboardingActivity;
import com.e.zguide.activities.RootActivity;
import com.e.zguide.repositories.SQLDataBaseRepository;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLDataBaseRepository.getInstance(this);

        boolean isVisited = SharedPreferencesRepository.getInstance(this).getIsVisited();

        Intent intent;

        if (isVisited) {
            intent = new Intent(this, RootActivity.class);
        } else {
            intent = new Intent(this, OnboardingActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}