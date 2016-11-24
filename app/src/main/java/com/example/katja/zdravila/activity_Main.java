package com.example.katja.zdravila;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.katja.zdravila.Seznam_zdravil.activity_Seznam_zdravil_rv;


public class activity_Main extends AppCompatActivity {

    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__main);


        app = (MyApplication) getApplication();
        app.load();
        if (app.getPodatki().getListZdravil().size() == 0) {
            app.setAll(app.getPodatki().getSeznamZdravilData());
            app.save();
        }

        Button btnDodajOpomnik = (Button) findViewById(R.id.btnDodajOpomnik);
        btnDodajOpomnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_Main.this, activity_Dodaj_opomnik.class));
            }
        });

        // odpre recyclerview
       Button btnMojaZdravila = (Button) findViewById(R.id.btnMojaZdravila);
        btnMojaZdravila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_Main.this, activity_Seznam_zdravil_rv.class));
            }
        });

    }

}
