package com.example.sweetvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    private Button btn;
    private EditText log, pass;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
                        if (log.getText().length() > 0 && pass.getText().length() > 0){
                            register(log.getText().toString(), pass.getText().toString());
                        }else{
                            Toast.makeText(sign_up.this,
                                    "The fields don't match",
                                    Toast.LENGTH_SHORT
                            ).show();

                            log.setText("");
                            pass.setText("");
                        }
                    }
                }
        );
    }
    private void register(final String login, final String password){
        Map<String, String> user = new HashMap<>();
        user.put("Login", login);
        user.put("Password", password);

        db.collection("Users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(sign_up.this, "Success!", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(sign_up.this, girls_boys_screen.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 400);
                    }
                });
    }
}