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

public class SpaceshipApplicationDatabaseManager extends SQLiteOpenHelper {
    // Database Config
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spaceshipRental";

    // "applications" Table Details
    private static final String TABLE_NAME = "applications";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PHONENUMBER = "phoneNumber";
    private static final String KEY_POSTALCODE = "postalCode";
    private static final String KEY_PASSWORD = "password";

    // Constructor
    SpaceshipApplicationDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Construct a SQL query to create a new table if it doesn't already exist.
        // Query format:
        //      CREATE TABLE IF NOT EXISTS table_name ( KEY_ID INTEGER PRIMARY KEY, KEY_1 type, KEY_2, type, ... )

        //      Where table_name is the name of the table and anything inside the parentheses describe the columns of the table
        //      PRIMARY KEY indicates that the column, KEY_ID, is to be used to uniquely identify the row in the table, and will be of type INTEGER
        //      KEY_1, KEY_2, etc are the names of the tables columns. Indicate the type of each column after the column name.
        //      e.g. if the entries in this column will be strings, then the type is TEXT

        String CREATE_APPLICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " TEXT," + KEY_PHONENUMBER + " TEXT," + KEY_POSTALCODE + " TEXT," + KEY_PASSWORD + " TEXT)";


        // Execute the above SQL instructions
        db.execSQL(CREATE_APPLICATIONS_TABLE);
    }

    // Don't worry about this method.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // Add an entry into the table. In this case, we're adding SpaceshipApplication objects.
    // Takes one argument, the SpaceshipApplication to add to the table.
    void addApplication(SpaceshipApplication spaceshipApplication) {
        // Open a writable instance of the database so we can make changes to it.
        SQLiteDatabase db = this.getWritableDatabase();

        // To insert a value into the database, we'll use a ContentValues object, which you can think of as a new blank row.
        ContentValues values = new ContentValues();
        // Filling in the details of this row
        values.put(KEY_EMAIL, spaceshipApplication.getEmail());
        values.put(KEY_PHONENUMBER, spaceshipApplication.getPhoneNumber());
        values.put(KEY_POSTALCODE, spaceshipApplication.getPostalCode());
        values.put(KEY_PASSWORD, spaceshipApplication.getPassword());

        // inserting the row which is filled in with the details from the SpaceshipApplication into the table.
        db.insert(TABLE_NAME, null, values);
        // Close the database that we opened so we don't run into weird memory problems.
        db.close();
    }

    // Return a list of all the rows in the database as SpaceshipApplication objects.
    ArrayList<SpaceshipApplication> getAllApplications() {
        ArrayList<SpaceshipApplication> appsList = new ArrayList<>();
        // we aren't making any changes so use a readable database, not a writable one.
        SQLiteDatabase db = this.getReadableDatabase();

        // A Cursor is used as a way to navigate a set of rows returned by a query to a database.
        // Query: SELECT * FROM TABLE_NAME
        // * is a shorthand for "everything".
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // If the query returns a non-zero amount of rows, the if block will execute. If nothing was returned,
        // i.e. the query resulted in 0 matching results, then cursor.moveToFirst() fails and returns false, skipping the if block.
        if (cursor.moveToFirst()) {
            // A do-while loop will always execute at least once, whereas a regular while loop checks the condition at the beginning and
            // can potentially never execute code inside itself.
            do {
                // From the row that the Cursor is currently looking at, make a new SpaceshipApplication object and fill in the details using info from the retrieved row
                SpaceshipApplication spaceshipApplication = new SpaceshipApplication(
                        Integer.parseInt(cursor.getString(0)), // id
                        cursor.getString(1), // email
                        cursor.getString(2), // phonenumber
                        cursor.getString(3), // postalcode
                        cursor.getString(4) // password
                );
                appsList.add(spaceshipApplication); // then add it to our list
            } while (cursor.moveToNext()); // Is there another row after this one? If not, we're done.
        }
        cursor.close();
        db.close();
        return appsList;
    }

    // Returns a SpaceshipApplication based on the unique ID.
    SpaceshipApplication getApplication(int id) {
        // we aren't making any changes so use a readable database, not a writable one.
        SQLiteDatabase db = this.getReadableDatabase();

        // AKA from TABLE_NAME, with details from these columns, I want to select the row with this ID
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                KEY_EMAIL, KEY_PHONENUMBER, KEY_POSTALCODE, KEY_PASSWORD}, KEY_ID + "=?",
                new String[]{ String.valueOf(id) }, null, null, null, null);
        if (cursor != null) { // If there are non-zero rows returned
            cursor.moveToFirst(); // go to the first one
        }
        cursor.close();
        return new SpaceshipApplication(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
    }
    // Given some spaceshipApplication, update the corresponding database entry with the same ID using the new values
    int updateApplication(SpaceshipApplication sa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, sa.getEmail());
        values.put(KEY_PASSWORD, sa.getPassword());
        values.put(KEY_PHONENUMBER, sa.getPhoneNumber());
        values.put(KEY_POSTALCODE, sa.getPostalCode());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(sa.getId())});
    }
    // Find the entry by ID and delete it.
    public void deleteApplication(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }
}
