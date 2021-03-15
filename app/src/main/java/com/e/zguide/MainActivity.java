package com.e.zguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.e.zguide.repositories.SQLDataBaseRepository;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class MainActivity extends AppCompatActivity {

    // TODO: 15.03.21 ADD APPLICATION AND INITIALIZE ALL REPOSITORIES FROM APPLICATION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesRepository.getInstance(this);
        SQLDataBaseRepository.getInstance(this);
        SQLDataBaseRepository.getDatabase(this);
    }
}