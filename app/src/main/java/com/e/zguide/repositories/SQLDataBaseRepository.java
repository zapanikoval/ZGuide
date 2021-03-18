package com.e.zguide.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.e.zguide.models.PlaceModel;

import java.util.ArrayList;
import java.util.UUID;

public class SQLDataBaseRepository extends SQLiteOpenHelper {
    public static final String DB_NAME = "Z_GUIDE_DATABASE";
    public static final String PLACES_TABLE = "PLACES";
    private static final int DB_VERSION = 1;

    private static SQLDataBaseRepository mInstance;

    protected SQLDataBaseRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLDataBaseRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SQLDataBaseRepository(context);
        }

        return mInstance;
    }
    public static SQLDataBaseRepository getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + PLACES_TABLE + " ("
                + PlaceModel.ID_KEY + " TEXT PRIMARY KEY, "
                + PlaceModel.NAME_KEY + " TEXT, "
                + PlaceModel.SHORT_DESC_KEY + " TEXT, "
                + PlaceModel.LONG_DESC_KEY + " TEXT, "
                + PlaceModel.COORDINATES_KEY + " TEXT, "
                + PlaceModel.IMAGE_URL_KEY + " TEXT, "
                + PlaceModel.PLACEMENT_KEY + " TEXT, "
                + PlaceModel.IS_FAVORITE_KEY + " INEGER);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Nullable
    public ArrayList<PlaceModel> getAllPlaces() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(PLACES_TABLE, null, null, null, null, null, null);

        ArrayList<PlaceModel> places = preparePlaces(cursor);
        cursor.close();
        database.close();
        return places;
    };
    @Nullable
    public ArrayList<PlaceModel> searchPlaces(String query) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(
                PLACES_TABLE,
                null,
                "name like ?",
                new String[] {query},
                null,
                null,
                null
        );

        ArrayList<PlaceModel> places = preparePlaces(cursor);
        cursor.close();
        database.close();
        return places;
    };

    public void setFavorite(UUID id, Boolean value) {
        String stringId = id.toString();
        ContentValues values = new ContentValues();
        values.put(PlaceModel.IS_FAVORITE_KEY, value ? 1 : 0);

        SQLiteDatabase database = getWritableDatabase();
        database.update(PLACES_TABLE, values, PlaceModel.ID_KEY + " = ?", new String[] {stringId});
        database.close();
    }

    @Nullable
    private ArrayList<PlaceModel> preparePlaces(Cursor cursor) {
        if (cursor == null) return null;

        ArrayList<PlaceModel> places = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                places.add(new PlaceModel(
                        cursor.getString(cursor.getColumnIndex(PlaceModel.ID_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.NAME_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.SHORT_DESC_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.LONG_DESC_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.COORDINATES_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.IMAGE_URL_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.PLACEMENT_KEY)),
                        cursor.getInt(cursor.getColumnIndex(PlaceModel.IS_FAVORITE_KEY)) == 1
                ));
            } while (cursor.moveToNext());
        }

        if (places.isEmpty()) {
            return null;
        } else {
            return places;
        }
    }
};
