package com.e.zguide.helpers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.zguide.models.PlaceModel;
import com.e.zguide.repositories.SQLDataBaseRepository;

import java.util.ArrayList;

public class PlacesViewModel extends ViewModel {
    MutableLiveData<ArrayList<PlaceModel>> placesList;
    MutableLiveData<ArrayList<PlaceModel>> searchPlaces;
    MutableLiveData<ArrayList<PlaceModel>> favoritePlaces;
    MutableLiveData<PlaceModel> selectedPlace;

    public LiveData<ArrayList<PlaceModel>> getPlaces() {
        return placesList;
    }

    public LiveData<ArrayList<PlaceModel>> getFavoritePlaces() {
        return favoritePlaces;
    }

    public PlacesViewModel() {
        super();
        loadPlaces();
        loadFavoritePlaces();
    }

    private void loadPlaces() {
        ArrayList<PlaceModel> placeModels = SQLDataBaseRepository.getInstance().getAllPlaces();
        placesList = new MutableLiveData<>();
        placesList.setValue(placeModels);
    }

    private void loadFavoritePlaces() {
        ArrayList<PlaceModel> favoritePlaces = SQLDataBaseRepository.getInstance().getFavoritePlaces();
        this.favoritePlaces = new MutableLiveData<>();
        this.favoritePlaces.setValue(favoritePlaces);
    }
}
