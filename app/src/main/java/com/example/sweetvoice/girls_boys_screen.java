package com.example.sweetvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class girls_boys_screen extends AppCompatActivity {

    private ImageView boy, girl;
    private String preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_boys_screen);
        getSupportActionBar().hide();
        Listener();
    }
    public void Listener(){
        boy = (ImageView)findViewById(R.id.imgBoy);
        girl = (ImageView)findViewById(R.id.imgGirl);

        boy.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences = "Boy";
                        Intent intent = new Intent(girls_boys_screen.this, age_screen.class);
                        startActivity(intent);
                        finish();

                    }
            }
        );
        girl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences = "Girl";
                        Intent intent = new Intent(girls_boys_screen.this, age_screen.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}