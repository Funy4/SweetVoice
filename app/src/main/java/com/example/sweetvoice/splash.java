package com.example.sweetvoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static java.lang.System.getProperty;

public class splash extends AppCompatActivity {

    final private String saved_class = "class";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            if(isNotNormalUser()){
                open("login_screen");
            }else{
                //checkClass();
                open("info");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void checkClass() {
        sPref = getPreferences(MODE_PRIVATE);
        if (!sPref.getString(saved_class, "").equals("")) {
            //если поле не пусто
            String a = sPref.getString(saved_class, "");
            Log.d("check", "\t" + a);
            open(a);
            Log.d("CHECK", "\tКласс уже существует");
        } else {
            Log.d("CHECK", "\tСоздался новый класс");
            openNew();
        }
    }

    private void open(String open) {
        Class<?> a = null;
        try {
            a = Class.forName("com.example.sweetvoice." + open);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final Class<?> finalA = a;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, finalA);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    private void openNew() {
        db.collection("Settings")
                .document("Af30VTuYRxyw1eTbWBnV")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            if (doc.exists()) {
                                String str_class;
                                str_class = doc.getData().get("After_Splash").toString();
                                //сохранение в память
                                sPref = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor ed = sPref.edit();
                                ed.putString(saved_class, str_class);
                                ed.apply();
                                Log.d("sPref", "Saved successful");
                                open(str_class);
                            } else {
                                Log.d("DB", "Not exists");
                            }
                        } else {
                            Toast.makeText((splash.this), "Problems with internet", Toast.LENGTH_SHORT).show();
                            open("Login_screen");
                            Log.d("DB", "ERROR with DB");
                        }
                    }
                });
    }

    private boolean isNotNormalUser() throws ClassNotFoundException {
        Boolean isEmulator;
        try {
            String[] words_for_exception = new String[15];
            words_for_exception[0] = "generic";
            words_for_exception[1] = "unknown";
            words_for_exception[2] = "goldfish";
            words_for_exception[3] = "ranchu";
            words_for_exception[4] = "google_sdk";
            words_for_exception[5] = "Emulator";
            words_for_exception[6] = "Android SDK built for x86";
            words_for_exception[7] = "Genymotion";
            words_for_exception[8] = "sdk_google";
            words_for_exception[9] = "google_sdk";
            words_for_exception[10] = "sdk";
            words_for_exception[11] = "sdk_x86";
            words_for_exception[12] = "vbox86p";
            words_for_exception[13] = "emulator";
            words_for_exception[14] = "simulator";

            try {
                Class SystemProperties = Class.forName("android.os.SystemProperties");
                TelephonyManager localTelephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

                //emulator + virtual device check
                for (String word : words_for_exception) {
                    if (Build.FINGERPRINT.startsWith(word)
                            || Build.HARDWARE.contains(word)
                            || Build.MODEL.contains(word)
                            || Build.MANUFACTURER.contains(word)
                            || Build.PRODUCT.contains(word)
                            || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                            || "google_sdk".equals(Build.PRODUCT)
                            || getProperty(String.valueOf(SystemProperties), "ro.kernel.qemu").equalsIgnoreCase("1")
                            || localTelephonyManager.getSimOperatorName().equals("Android")
                            || localTelephonyManager.getNetworkOperatorName().equals("Android")
                    ) {
                        isEmulator = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //vpn check
            ArrayList arrayList = new ArrayList();
            try {
                Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.isUp()) {
                        arrayList.add(networkInterface.getName());
                    }
                }
            } catch (Exception unused) {
                unused.printStackTrace();
            }
            if (!arrayList.contains("tun0")) {
                isEmulator = false;
            } else {
                isEmulator = true;
            }
            //Log.d("Check", "Is true? " + isEmulator);
            return isEmulator;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isEmulator = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        sPref = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor ed = sPref.edit();
//        ed.putString(saved_class, "");
//        ed.apply();
    }
}