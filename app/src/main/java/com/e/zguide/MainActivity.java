package com.e.zguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.e.zguide.repositories.SQLDataBaseRepository;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        SharedPreferencesRepository.getInstance(this);
        SQLDataBaseRepository.getInstance(this);
    }
}