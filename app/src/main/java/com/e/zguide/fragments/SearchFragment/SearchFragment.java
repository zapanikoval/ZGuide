package com.e.zguide.fragments.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentSearchBinding;
import com.e.zguide.helpers.IOnPlacePressCallback;
import com.e.zguide.helpers.PlaceProvider;
import com.e.zguide.helpers.PlacesViewModel;
import com.e.zguide.models.PlaceModel;

public class SearchFragment extends Fragment implements IOnPlacePressCallback {
    FragmentSearchBinding binding;
    NavController navController;
    PlacesViewModel viewModel;
    PlaceProvider placesProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(PlacesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        placesProvider = new PlaceProvider(this, viewModel.getSearchedPlaces().getValue());
        binding.placesRecyclerView.setAdapter(placesProvider);

        viewModel.getSearchedPlaces().observe(getViewLifecycleOwner(), (places) -> {
            placesProvider.setPlaces(places);
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                viewModel.searchPlacesByQuery(s);
                return false;
            }
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
        navController.navigate(R.id.action_searchFragment_to_placeDetailsFragment);
    }
}