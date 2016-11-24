package com.example.katja.zdravila.Seznam_zdravil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.example.katja.zdravila.MyApplication;
import com.example.katja.zdravila.R;
import com.example.katja.zdravila.activity_Novo_zdravilo;

public class activity_Seznam_zdravil_rv extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_POIMENOVANJE = "BUNDLE_IME";
    private static final String EXTRA_ZALOGA = "BUNDLE_ZALOGA";

    private RecyclerView recView;
    private seznam_adapter adapter;
    public Button gumb_dodaj;
    MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_seznam_zdravil_rv);
        app = (MyApplication) getApplication();
        recView = (RecyclerView) findViewById(R.id.rec_list);

        // vrsta layouta
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new seznam_adapter(app.getPodatki(), this, this);
        recView.setAdapter(adapter);

        gumb_dodaj = (Button) findViewById(R.id.btn_dodaj);
        gumb_dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(activity_Seznam_zdravil_rv.this, activity_Novo_zdravilo.class), 1);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                adapter.notifyDataSetChanged();
                app.save();
            } else {
                app.save();
            }

        }
    }
}




