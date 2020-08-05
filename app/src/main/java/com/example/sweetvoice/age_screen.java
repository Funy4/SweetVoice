package com.example.sweetvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class age_screen extends AppCompatActivity {

    private EditText age;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_screen);
        getSupportActionBar().hide();

        Listener();
    }
    public void Listener(){
        btn = (Button)findViewById(R.id.button);
        age = (EditText)findViewById(R.id.age);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(age.getText().toString()) > 18){
                            Intent i = new Intent(age_screen.this, wait_screen.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(age_screen.this,
                                    "You must be over 18 years old.",
                                    Toast.LENGTH_SHORT).show();

                            age.setText("");
                        }
                    }
                }
        );
    }
}