package com.example.sweetvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class login_screen extends AppCompatActivity {


    private TextView sign_up;
    private Button btn;
    private EditText log, pass;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //checkUser("dsa","12345");
        Listener();
    }
    public void Listener(){
        sign_up = (TextView)findViewById(R.id.forgTxt);
        btn = (Button)findViewById(R.id.btnNext);
        log = (EditText)findViewById(R.id.txtLog);
        pass = (EditText)findViewById(R.id.txtPass);


        sign_up.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(login_screen.this, sign_up.class);
                        startActivity(intent);
                    }
                }
        );

        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        if (log.getText().length() > 0 && pass.getText().length() > 0) {
                            checkUser(log.getText().toString(), pass.getText().toString());
                        }else
                            Toast.makeText(login_screen.this,
                                    "Invalid username or password",
                                    Toast.LENGTH_SHORT).show();
                    }
                }
        );
        }

    private void checkUser(final String login, final String password) {
        db.collection("Users")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //DocumentSnapshot doc = task.getResult();
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if(login.equals(doc.getData().get("Login")) && password.equals(doc.getData().get("Password"))){
                                    Intent intent = new Intent(login_screen.this, girls_boys_screen.class);
                                    startActivity(intent);
                                    finish();
                                }else{

                                    Toast.makeText(login_screen.this,
                                            "Invalid username or password",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    log.setText("");
                                    pass.setText("");
                                }
                               Log.d("DB", doc.getId() + " => " + doc.getData());
                            }


                        }else {
                            Toast.makeText((login_screen.this), "Problems with internet", Toast.LENGTH_SHORT).show();
                            Log.d("DB", "ERROR with DB");
                        }
                    }
                });


    }


}