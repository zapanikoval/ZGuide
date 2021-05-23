package com.e.zguide.helpers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.zguide.databinding.ListItemBinding;
import com.e.zguide.models.PlaceModel;

import java.util.ArrayList;

public class PlaceProvider extends RecyclerView.Adapter<PlaceProvider.ViewHolder> {
    private ArrayList<PlaceModel> placeModels;
    private final IOnPlacePressCallback callbackOwner;

    public PlaceProvider(IOnPlacePressCallback onPlacePressCallback, ArrayList<PlaceModel> placeModelArrayList) {
        this.callbackOwner = onPlacePressCallback;
        this.placeModels = placeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaceModel place = placeModels.get(position);

        Glide.with(holder.binding.getRoot()).load(place.getImageUrl()).into(holder.binding.placeImage);

        holder.binding.placeName.setText(place.getName());
        holder.binding.placeShortDescription.setText(place.getShortDescription());
        holder.binding.placeCard.setOnClickListener((view) -> {
            callbackOwner.onPlaceClick(place, position);
        });
    }

    @Override
    public int getItemCount() {
        return placeModels.size();
    }

    public void setPlaces(ArrayList<PlaceModel> newPlaces) {
        this.placeModels = newPlaces;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
