package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database and table information
    private static final String DATABASE_NAME = "LocationFinderDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOCATIONS = "locations";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table SQL query
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ADDRESS + " TEXT NOT NULL, "
                + COLUMN_LATITUDE + " REAL NOT NULL, "
                + COLUMN_LONGITUDE + " REAL NOT NULL" + ")";
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    // Method to add a new location
    public boolean addLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);

        long result = db.insert(TABLE_LOCATIONS, null, values);
        db.close();
        return result != -1; // Return true if insert was successful
    }

    // Method to update a location
    public boolean updateLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);

        int rowsAffected = db.update(TABLE_LOCATIONS, values, COLUMN_ADDRESS + " = ?", new String[]{address});
        return rowsAffected > 0; // Returns true if at least one row was updated
    }


    // Method to delete a location
    public boolean deleteLocation(String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_LOCATIONS, COLUMN_ADDRESS + " = ?", new String[]{address});
        return rowsDeleted > 0; // Returns true if at least one row was deleted
    }


    // Method to query a location by address
    public Cursor getLocationByAddress(String address) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATIONS,
                new String[]{COLUMN_LATITUDE, COLUMN_LONGITUDE},
                COLUMN_ADDRESS + " = ?",
                new String[]{address},
                null, null, null);
    }
}
