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

        spaceshipApplicationDatabaseManager = new SpaceshipApplicationDatabaseManager(getApplicationContext());

        linearLayout = findViewById(R.id.applicationListLinearLayout);

        ArrayList<SpaceshipApplication> apps = spaceshipApplicationDatabaseManager.getAllApplications();

        for (int i = 0; i < apps.size(); i++) {
            SpaceshipApplication sa = apps.get(i);
            TextView t = new TextView(getApplicationContext());
            t.setText(sa.toString());
            final int update_id = sa.getId();
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
