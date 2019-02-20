package com.example.spaceadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_form);

        sam = new SpaceshipApplicationManager(getApplicationContext());
        submit_button = findViewById(R.id.submit_button);
        email_text = findViewById(R.id.email_text);
        phoneNumber_text = findViewById(R.id.phone_text);
        postalCode_text = findViewById(R.id.postalCode_text);
        password_text = findViewById(R.id.password_text);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_spaceship_form);
                String email = email_text.getText().toString();
                String phoneNumber = phoneNumber_text.getText().toString();
                String postalCode = postalCode_text.getText().toString();
                String password = password_text.getText().toString();

                SpaceshipApplication sa = new SpaceshipApplication(1, email, phoneNumber, postalCode, password);

                sam.addApplication(sa);

                setContentView(R.layout.activity_main);

            }

        });
    }
}
