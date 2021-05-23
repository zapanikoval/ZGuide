package com.e.zguide.fragments.PlaceDetails;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.e.zguide.R;
import com.e.zguide.activities.RootActivity;
import com.e.zguide.databinding.FragmentPlaceDetailsBinding;
import com.e.zguide.helpers.PlacesViewModel;
import com.e.zguide.models.PlaceModel;

public class PlaceDetailsFragment extends Fragment {
    FragmentPlaceDetailsBinding binding;
    PlacesViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(PlacesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlaceDetailsBinding.inflate(inflater, container, false);

        viewModel.getSelectedPlace().observe(getViewLifecycleOwner(), (place) -> {
            ((RootActivity) getActivity()).getSupportActionBar().setTitle(place.getName());
            Glide.with(this).load(place.getImageUrl()).into(binding.placeImage);
            binding.placeName.setText(place.getName());
            binding.longDescription.setText(place.getLongDescription());

            if (place.getFavorite()) {
                binding.makeFavoriteBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.red));
            } else {
                binding.makeFavoriteBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.gray));
            }
        });

        binding.makeFavoriteBtn.setOnClickListener((view) -> {
            viewModel.setSelectedPlaceAsFavorite();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}