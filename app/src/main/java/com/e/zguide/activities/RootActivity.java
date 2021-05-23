package com.e.zguide.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.e.zguide.R;
import com.e.zguide.databinding.ActivityRootBinding;
import com.e.zguide.helpers.PlacesViewModel;

public class RootActivity extends AppCompatActivity {

    ActivityRootBinding binding;
    PlacesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRootBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(PlacesViewModel.class);
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.placesFragment, R.id.favoritesFragment, R.id.searchFragment
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomTabView, navController);
    }
}