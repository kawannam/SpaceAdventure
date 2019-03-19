package com.example.spaceadventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";


    // UI Elements to bind
    Button blast_off_button;
    Button rent_button;
    Button view_all_button;
    TextView blast_off_text;

    // Other
    int button_press_count;

    // For Local Persistent Variables
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ; //K1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // Binding UI Elements
        blast_off_button = findViewById(R.id.blast_off_button);
        blast_off_text = findViewById(R.id.blast_off_text);
        view_all_button = findViewById(R.id.view_all_button);
        rent_button = findViewById(R.id.rent_button);

        button_press_count = 0;

        // blast_off_button OnClick behaviour
        blast_off_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_press_count = button_press_count + 1;

                // Toggles the displayed text, and on the button.
                if (blast_off_text.getText() == "Hello World!") {
                    blast_off_text.setText("See you later");
                    blast_off_button.setText("Land");
                } else {
                    blast_off_text.setText("Hello World!");
                    blast_off_button.setText("Blast Off!");
                }
            }
        });

        // rent_button OnClick behaviour
        rent_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start a new activity to fill in a spaceship rental form.
                Intent i = new Intent(getApplicationContext(), RentSpaceshipFormActivity.class);
                startActivity(i);
            }

        });

        // view_all_button OnClick behaviour
        view_all_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start a new activity to view all the rental applications in the database.
                Intent i = new Intent(getApplicationContext(), SpaceshipApplicationListActivity.class);
                startActivity(i);
            }
        });
    }
}
