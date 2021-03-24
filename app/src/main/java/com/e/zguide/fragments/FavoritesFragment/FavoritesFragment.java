package com.e.zguide.fragments.FavoritesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentFavoritesBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding binding;
    NavController navController;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        binding.btnGoToSearch.setOnClickListener(v -> navController.navigate(R.id.searchFragment));

        binding.btnGoToPlaces.setOnClickListener(v -> navController.navigate(R.id.placesFragment));

        return binding.getRoot();
    }
}