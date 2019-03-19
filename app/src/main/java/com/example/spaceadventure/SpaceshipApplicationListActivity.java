package com.example.spaceadventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SpaceshipApplicationListActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    SpaceshipApplicationDatabaseManager spaceshipApplicationDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_application_list);

        // Create a new instance of the database manager so we can get the rental applications
        spaceshipApplicationDatabaseManager = new SpaceshipApplicationDatabaseManager(getApplicationContext());

        // Bind the linear layout so we can programmatically add views into it.
        linearLayout = findViewById(R.id.applicationListLinearLayout);

        // Retrieve data from database, create an ArrayList of new SpaceshipApplication objects using that data
        ArrayList<SpaceshipApplication> allApplications = spaceshipApplicationDatabaseManager.getAllApplications();

        // Iterating through list of applications:
        // For each application,
        //      Reference a SpaceshipApplication object using the data from the database
        //      Create a new TextView, display the SpaceshipApplication's data as the TextView's text
        //      Set the OnClick behaviour of the TextView so that if clicked, the user can edit and update that
        //          application's data.
        //      Add the TextView to the LinearLayout

        for (int i = 0; i < allApplications.size(); i++) {
            SpaceshipApplication spaceshipApplication = allApplications.get(i);
            TextView t = new TextView(getApplicationContext());
            t.setText(spaceshipApplication.toString());
            final int update_id = spaceshipApplication.getId();
            t.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), RentSpaceshipFormActivity.class);
                    intent.putExtra("id", update_id);
                    startActivity(intent);
                }});
            linearLayout.addView(t);
        }

    }
}
