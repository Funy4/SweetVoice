package com.example.sweetvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_screen extends AppCompatActivity {


    private TextView forgot;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        Listener();
    }
    public void Listener(){
        forgot = (TextView)findViewById(R.id.forgTxt);
        btn = (Button)findViewById(R.id.btnNext);

        forgot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Login_screen.this, new_password.class);
                        startActivity(intent);
                    }
                }
        );

        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(Login_screen.this, girls_boys_screen.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        };
}