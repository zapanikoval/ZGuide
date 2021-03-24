package com.e.zguide.fragments.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentSecondStepBinding;

public class SecondStep extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSecondStepBinding binding = FragmentSecondStepBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(container);

        binding.btnNextPress.setOnClickListener(v -> navController.navigate(R.id.action_secondStep_to_thirdStep));

        return binding.getRoot();
    }
}