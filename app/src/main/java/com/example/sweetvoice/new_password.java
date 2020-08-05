package com.example.sweetvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class new_password extends AppCompatActivity {

    private Button btn;
    private EditText log, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        getSupportActionBar().hide();
        Listenner();
    }
    public void Listenner(){
        btn = (Button)findViewById(R.id.btnNext);
        log = (EditText)findViewById(R.id.txtLog);
        pass = (EditText)findViewById(R.id.txtPass);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (log.equals(pass) && log.length() > 0 && pass.length() > 0){
                            Intent intent = new Intent(new_password.this, girls_boys_screen.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(new_password.this,
                                    "Поля не совпадают",
                                    Toast.LENGTH_SHORT
                            ).show();

                            log.setText("");
                            pass.setText("");
                        }
                    }
                }
        );
    }
}