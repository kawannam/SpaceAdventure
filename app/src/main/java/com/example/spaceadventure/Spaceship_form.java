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

public class Spaceship_form extends AppCompatActivity {

    SpaceshipApplicationManager sam;

    Button submit_button;
    EditText email_text;
    EditText phoneNumber_text;
    EditText postalCode_text;
    EditText password_text;

    public static final String MyPREFERENCES = "SpaceshipAdventures" ; //K1
    public static final String NEXT_ID = "ID_Key";

    SharedPreferences sharedpreferences; //K2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_form);
        sam = new SpaceshipApplicationManager(getApplicationContext());

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE); //K3


        submit_button = findViewById(R.id.submit_button);
        email_text = findViewById(R.id.email_text);
        phoneNumber_text = findViewById(R.id.phone_text);
        postalCode_text = findViewById(R.id.postalCode_text);
        password_text = findViewById(R.id.password_text);



        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.commit();

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("ATTENTION","SUBMIT BUTTON CLICKED");
                setContentView(R.layout.activity_spaceship_form);
                String email = email_text.getText().toString();
                String phoneNumber = phoneNumber_text.getText().toString();
                String postalCode = postalCode_text.getText().toString();
                String password = password_text.getText().toString();

                int id = sharedpreferences.getInt(NEXT_ID, 0); //K4

                SpaceshipApplication sa = new SpaceshipApplication(id, email, phoneNumber, postalCode, password);

                SharedPreferences.Editor editor = sharedpreferences.edit(); //K5
                editor.putInt(NEXT_ID, id+1);
                editor.commit();

                sam.addApplication(sa);

                finish();

            }

        });
    }
}
