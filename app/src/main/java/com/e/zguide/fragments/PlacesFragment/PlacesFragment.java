package com.e.zguide.fragments.PlacesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.zguide.R;
import com.e.zguide.databinding.FragmentPlacesBinding;
import com.e.zguide.helpers.IOnPlacePressCallback;
import com.e.zguide.helpers.PlaceProvider;
import com.e.zguide.helpers.PlacesViewModel;
import com.e.zguide.models.PlaceModel;

public class PlacesFragment extends Fragment implements IOnPlacePressCallback {
    FragmentPlacesBinding binding;
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
        // Inflate the layout for this fragment
        binding = FragmentPlacesBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        placesProvider = new PlaceProvider(this, viewModel.getPlaces().getValue());
        binding.placesRecyclerView.setAdapter(placesProvider);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPlaceClick(PlaceModel place, int position) {

    }
}