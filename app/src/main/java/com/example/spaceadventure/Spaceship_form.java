package com.example.spaceadventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Spaceship_form extends AppCompatActivity {


    //Database Variables
    SpaceshipApplicationManager sam;
    int update_id;

    //User Interface Variables
    Button submit_button;
    EditText email_text;
    EditText phoneNumber_text;
    EditText postalCode_text;
    EditText password_text;


    //Persistent (Variables that you want to last after a user closes the app) Variables
    public static final String MyPREFERENCES = "SpaceshipAdventures" ; //The "folder" where your app data is saved on the phone
    public static final String NEXT_ID = "ID_Key"; //The "file" where your variable is save
    SharedPreferences sharedpreferences; //The class that helps save things to your phone

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_form);
        sam = new SpaceshipApplicationManager(getApplicationContext());

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE); //K3
        // binding UI elements
        submit_button = findViewById(R.id.submit_button);
        email_text = findViewById(R.id.email_text);
        phoneNumber_text = findViewById(R.id.phone_text);
        postalCode_text = findViewById(R.id.postalCode_text);
        password_text = findViewById(R.id.password_text);

        // If this was launched from Rent Spaceship, there won't be an id field in the intent, i.e. update_id = -1. We want to make a new application
        // But if this was launched from Application List, then update_id will be >= 0. We want to update and existing application
        Intent intent = getIntent();
        update_id = intent.getIntExtra("id", -1);
        if (update_id > -1) {
            submit_button.setText("Update");
        }

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.commit();

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = email_text.getText().toString();
                String phoneNumber = phoneNumber_text.getText().toString();
                String postalCode = postalCode_text.getText().toString();
                String password = password_text.getText().toString();

                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;

                if (update_id < 0) {
                    int id = sharedpreferences.getInt(NEXT_ID, 0); //K4

                    SpaceshipApplication sa = new SpaceshipApplication(id, email, phoneNumber, postalCode, password);
                    SharedPreferences.Editor editor = sharedpreferences.edit(); //K5
                    editor.putInt(NEXT_ID, id+1);
                    editor.commit();
                    sam.addApplication(sa);

                    text = "Application Submitted!";
                }
                else {
                    SpaceshipApplication sa = new SpaceshipApplication(update_id, email, phoneNumber, postalCode, password);
                    sam.updateApplication(sa);
                    text = "Application Updated!";

                }

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();

            }

        });
    }
}
