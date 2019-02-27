package com.example.spaceadventure;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApplicationListActivity extends AppCompatActivity {

    LinearLayout ll;
    SpaceshipApplicationManager sam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        sam = new SpaceshipApplicationManager(getApplicationContext());
        ll = findViewById(R.id.ll);

        ArrayList<SpaceshipApplication> apps = sam.getAllApplications();

        for (int i = 0; i < apps.size(); i++) {
            TextView t = new TextView(getApplicationContext());
            t.setText(apps.get(i).toString());
            ll.addView(t);
        }

    }
}
