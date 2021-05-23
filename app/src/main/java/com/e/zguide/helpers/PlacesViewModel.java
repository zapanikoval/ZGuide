package com.e.zguide.helpers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.zguide.models.PlaceModel;
import com.e.zguide.repositories.SQLDataBaseRepository;

import java.util.ArrayList;

public class PlacesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<PlaceModel>> placesList;
    private MutableLiveData<ArrayList<PlaceModel>> searchPlaces;
    private MutableLiveData<ArrayList<PlaceModel>> favoritePlaces;
    private MutableLiveData<PlaceModel> selectedPlace = new MutableLiveData<>();

    public LiveData<ArrayList<PlaceModel>> getPlaces() {
        return placesList;
    }
    public LiveData<ArrayList<PlaceModel>> getFavoritePlaces() {
        return favoritePlaces;
    }
    public LiveData<PlaceModel> getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(PlaceModel selectedPlace) {
        this.selectedPlace.setValue(selectedPlace);
    }
    public void setSelectedPlaceAsFavorite() {
        PlaceModel place = selectedPlace.getValue();
        place.setFavorite(!place.getFavorite());
        SQLDataBaseRepository.getInstance().setFavorite(place.getId(), place.getFavorite());

        selectedPlace.setValue(place);
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
