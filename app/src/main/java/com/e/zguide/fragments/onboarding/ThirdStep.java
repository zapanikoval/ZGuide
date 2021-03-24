package com.e.zguide.fragments.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentThirdStepBinding;
import com.e.zguide.repositories.SharedPreferencesRepository;

public class ThirdStep extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentThirdStepBinding binding = FragmentThirdStepBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.onboarding_nav_host);

        binding.btnNextPress.setOnClickListener(v -> {
            //todo clear task
//            SharedPreferencesRepository.getInstance().setIsVisited(true);
            navController.navigate(R.id.action_thirdStep_to_rootActivity);
        });

        return binding.getRoot();
    }
}