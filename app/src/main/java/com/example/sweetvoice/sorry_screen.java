package com.example.sweetvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sorry_screen extends AppCompatActivity {

    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorry_screen);

        Listenner();

    }

    private void Listenner() {
        txt = (TextView) findViewById(R.id.textView5);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sorry_screen.this, splash.class);
                startActivity(i);
                finish();
            }
        });
    }
}