package com.e.zguide.fragments.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentThirdStepBinding;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class ThirdStep extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentThirdStepBinding binding = FragmentThirdStepBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(container);

        binding.btnNextPress.setOnClickListener(v -> {
            SharedPreferencesRepository.getInstance().setIsVisited(true);
            navController.navigate(R.id.action_thirdStep_to_rootActivity);
        });

        return binding.getRoot();
    }
}