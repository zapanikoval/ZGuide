package com.e.zguide.repositories;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.e.zguide.models.PlaceModel;

import java.util.List;

@Database(entities = {AppDatabase.Place.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();


    //Entities
    @Entity
    public static class Place {
        public static final String ENTITY_NAME = "PLACES";

        @PrimaryKey
        public String id;

        public String name, shortDescription, longDescription, coordinates, imageUrl, placement;
        public Boolean isFavorite;
    }

    //Daos
    @Dao
    public interface PlaceDao {
        @Query("SELECT * FROM " + Place.ENTITY_NAME)
        List<Place> getAll();

        @Query("SELECT * FROM " + Place.ENTITY_NAME + " WHERE " + PlaceModel.ID_KEY + " = :id")
        Place getById(String id);

        @Query("SELECT * FROM " + Place.ENTITY_NAME + " WHERE " + PlaceModel.NAME_KEY + " like :query")
        List<Place> searchPlaces(String query);

        @Query("UPDATE " + Place.ENTITY_NAME +
                " SET " + PlaceModel.IS_FAVORITE_KEY + " = :value " +
                "WHERE " + PlaceModel.ID_KEY + " = :id")
        void setFavorite(String id, int value);

        @Insert
        void insert(Place place);

        @Update
        void update(Place place);

        @Delete
        void delete(Place place);
    }
}
