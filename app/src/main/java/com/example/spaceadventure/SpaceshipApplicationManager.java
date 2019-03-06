package com.example.spaceadventure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.widget.Space;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipApplicationManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spaceshipRental";
    private static final String TABLE_NAME = "applications";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PHONENUMBER = "phoneNumber";
    private static final String KEY_POSTALCODE = "postalCode";
    private static final String KEY_PASSWORD = "password";

    public SpaceshipApplicationManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_APPLICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " TEXT," + KEY_PHONENUMBER + " TEXT," + KEY_POSTALCODE + " TEXT," + KEY_PASSWORD + " TEXT)";

        db.execSQL(CREATE_APPLICATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    void addApplication(SpaceshipApplication spaceshipApplication) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, spaceshipApplication.email);
        values.put(KEY_PHONENUMBER, spaceshipApplication.phoneNumber);
        values.put(KEY_POSTALCODE, spaceshipApplication.postalCode);
        values.put(KEY_PASSWORD, spaceshipApplication.password);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<SpaceshipApplication> getAllApplications() {
        ArrayList<SpaceshipApplication> appsList = new ArrayList<SpaceshipApplication>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                SpaceshipApplication sa = new SpaceshipApplication(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                appsList.add(sa);
            } while (cursor.moveToNext());
        }

        return appsList;
    }

    SpaceshipApplication getApplication(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                KEY_EMAIL, KEY_PHONENUMBER, KEY_POSTALCODE, KEY_PASSWORD}, KEY_ID + "=?",
                new String[]{ String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        SpaceshipApplication spaceshipApplication = new SpaceshipApplication(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return spaceshipApplication;
    }

    public int updateApplication(SpaceshipApplication sa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, sa.getEmail());
        values.put(KEY_PASSWORD, sa.getPassword());
        values.put(KEY_PHONENUMBER, sa.getPhoneNumber());
        values.put(KEY_POSTALCODE, sa.getPostalCode());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(sa.getId())});
    }

    public void deleteApplication(SpaceshipApplication sa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[] {String.valueOf(sa.getId())});
        db.close();
    }
}
