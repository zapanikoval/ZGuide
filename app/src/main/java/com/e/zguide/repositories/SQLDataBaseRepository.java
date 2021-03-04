package com.e.zguide.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.zguide.models.PlaceModel;

import java.util.ArrayList;

public class SQLDataBaseRepository extends SQLiteOpenHelper {
    public static final String DB_NAME = "Z_GUIDE_DATABASE";
    public static final String PLACES_TABLE = "PLACES";

    private static SQLDataBaseRepository mInstance;

    protected SQLDataBaseRepository(Context context) {
        super(context, DB_NAME, null, 1);
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
                + PlaceModel.PLACEMENT_KEY + " TEXT);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<PlaceModel> getAllPlaces() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(PLACES_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            ArrayList<PlaceModel> places = new ArrayList<>();

            do {
                places.add(new PlaceModel(
                        cursor.getString(cursor.getColumnIndex(PlaceModel.ID_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.NAME_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.SHORT_DESC_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.LONG_DESC_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.COORDINATES_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.IMAGE_URL_KEY)),
                        cursor.getString(cursor.getColumnIndex(PlaceModel.PLACEMENT_KEY))
                ));
            } while (cursor.moveToNext());

            cursor.close();
            database.close();
            return places;
        } else {
            cursor.close();
            database.close();
            return null;
        }
    }
}
