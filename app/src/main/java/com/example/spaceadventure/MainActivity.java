package com.example.spaceadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button my_first_button;
    Button rent_button;
    TextView my_first_text;
    int button_press_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        my_first_button = findViewById(R.id.my_first_button);
        my_first_text = findViewById(R.id.my_first_text);
        rent_button = findViewById(R.id.rent_button);


        button_press_count = 0;

        my_first_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_press_count = button_press_count + 1;
                if (my_first_text.getText() == "Hello World!") {
                    my_first_text.setText("See you later");
                    my_first_button.setText("Land");
                } else {
                    my_first_text.setText("Hello World!");
                    my_first_button.setText("Blast Off!");
                }
            }
        });


        rent_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_spaceship_form);
            }

        });
    }
}
/* button_press_count = button_press_count + 1;
                if (my_first_text.getText() == "Hello World!") {
                    my_first_text.setText("See you later!");
                    my_first_button.setText("Land");
                } else {
                    my_first_text.setText("Hello World!");
                    my_first_button.setText("Blast Off");
                }*/

/*
        });*/

//rent_button = findViewById(R.id.rent_button);