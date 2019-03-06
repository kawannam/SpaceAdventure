package com.example.spaceadventure;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
            SpaceshipApplication sa = apps.get(i);
            TextView t = new TextView(getApplicationContext());
            t.setText(sa.toString());
            final int update_id = sa.getId();
            t.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), Spaceship_form.class);
                    intent.putExtra("id", update_id);
                    startActivity(intent);
                }});
            ll.addView(t);
        }

    }
}
