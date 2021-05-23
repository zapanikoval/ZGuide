package com.e.zguide.fragments.FavoritesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentFavoritesBinding;
import com.e.zguide.helpers.IOnPlacePressCallback;
import com.e.zguide.helpers.PlaceProvider;
import com.e.zguide.helpers.PlacesViewModel;
import com.e.zguide.models.PlaceModel;

import java.util.Objects;

public class FavoritesFragment extends Fragment implements IOnPlacePressCallback {
    FragmentFavoritesBinding binding;
    NavController navController;
    PlacesViewModel viewModel;
    PlaceProvider provider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(PlacesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        provider = new PlaceProvider(this, viewModel.getFavoritePlaces().getValue());
        binding.placesRecyclerView.setAdapter(provider);

        viewModel.getFavoritePlaces().observe(getViewLifecycleOwner(), (places) -> {
            Log.d("OBSERVE FAVORITE PLACES", places.toString());
            provider.setPlaces(places);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPlaceClick(PlaceModel place, int position) {
        viewModel.setSelectedPlace(place);
        navController.navigate(R.id.action_favoritesFragment_to_placeDetailsFragment);
    }
}