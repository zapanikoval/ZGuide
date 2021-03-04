package com.e.zguide.models;

import java.util.UUID;

public class PlaceModel {
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String SHORT_DESC_KEY = "shortDescription";
    public static final String LONG_DESC_KEY = "longDescription";
    public static final String COORDINATES_KEY = "coordinates";
    public static final String IMAGE_URL_KEY = "imageUrl";
    public static final String PLACEMENT_KEY = "placement";
    public static final String IS_FAVORITE_KEY = "isFavorite";

    private UUID id;
    private String name, shortDescription, longDescription, coordinates, imageUrl, placement;
    private Boolean isFavorite;

    public PlaceModel(String id, String name, String shortDescription, String longDescription, String coordinates, String imageUrl, String placement, Boolean isFavorite) {
        if (id == null) {
            this.id = UUID.randomUUID();
        } else {
            this.id = UUID.fromString(id);
        }
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.coordinates = coordinates;
        this.imageUrl = imageUrl;
        this.placement = placement;

        if (isFavorite == null) {
            this.isFavorite = false;
        } else {
            this.isFavorite = isFavorite;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPlacement() {
        return placement;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
