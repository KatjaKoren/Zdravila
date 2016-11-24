package com.example.katja.zdravila;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Zdravilo;
import com.example.katja.zdravila.Seznam_zdravil.activity_Seznam_zdravil_rv;
import com.example.katja.zdravila.Skener_kode.IntentIntegrator;
import com.example.katja.zdravila.Skener_kode.IntentResult;
import com.example.katja.zdravila.Skener_kode.asinhrono_Novo_zdravilo;

public class activity_Novo_zdravilo extends AppCompatActivity implements View.OnClickListener {


    // skeniranje kode
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private MyApplication app;
    int message=-1;
    private Zdravilo vhod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__novo_zdravilo);

        scanBtn = (Button)findViewById(R.id.btn_scan);

        EditText poimenovanje=(EditText) findViewById(R.id.inp_poimenovanje);
        EditText kolicina=(EditText) findViewById(R.id.inp_kolicina);
        EditText oblika=(EditText) findViewById(R.id.inp_oblika);
        EditText namen=(EditText) findViewById(R.id.inp_namen);
        //EditText merska_enota= (EditText) findViewById(R.id.inp_kolicina);
        EditText st_tablet = (EditText) findViewById(R.id.inp_stevilo_tbl);

        scanBtn.setOnClickListener((View.OnClickListener) this);
        Zdravilo vrnjeno;
        app = (MyApplication) getApplication();

        Intent intent = getIntent();
        if (intent!=null) {
            message = intent.getIntExtra("vhod",-1);
            if(message!=-1)
            {
                vrnjeno= app.getPodatki().getZdravilo(message);
                if (vrnjeno!=null) {
                    poimenovanje.setText(vrnjeno.getPoimenovanje());
                    kolicina.setText(String.valueOf(vrnjeno.getKolicina()));
                    oblika.setText(vrnjeno.getOblika());
                    namen.setText(vrnjeno.getNamen());
                    //merska_enota.setText(vrnjeno.getMerska_enota());
                    st_tablet.setText(String.valueOf(vrnjeno.getSt_tablet()));
                }
            }
        }
    }

    // skeniranje kode
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //imamo rezultat
            String scanContent = scanningResult.getContents();

            String url = "http://www.google.si/search?q="+scanContent;
            asinhrono_Novo_zdravilo skenirano_novo = new asinhrono_Novo_zdravilo(this,url);
            skenirano_novo.execute();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Koda ni bila zaznana", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_scan){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

   // vnos zdravila preko obrazca (ročno)
    public void input_zdravilo(View view) {
        EditText poimenovanje=(EditText) findViewById(R.id.inp_poimenovanje);
        EditText odmerek=(EditText) findViewById(R.id.inp_kolicina);
        EditText oblika=(EditText) findViewById(R.id.inp_oblika);
        EditText namen=(EditText) findViewById(R.id.inp_namen);
        EditText merska_enota= (EditText) findViewById(R.id.inp_kolicina);
        EditText st_tablet = (EditText) findViewById(R.id.inp_stevilo_tbl);

        Zdravilo zdravilo = new Zdravilo(poimenovanje.getText().toString(), 0 ,merska_enota.getText().toString(),oblika.getText().toString(), namen.getText().toString(), Integer.parseInt(st_tablet.getText().toString()) );
        if(message!=-1)
            app.getPodatki().getListZdravil().set(message,zdravilo);
        else
            app.getPodatki().dodaj(zdravilo);

        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }

    // vnos zdravila preko skenirane kode
    public void napolni(Zdravilo vhod)
    {
        if (vhod.getPoimenovanje() != "zdravilo ni bilo najdeno")
        {
            EditText poimenovanje=(EditText) findViewById(R.id.inp_poimenovanje);
            EditText kolicina=(EditText) findViewById(R.id.inp_kolicina);
            EditText oblika=(EditText) findViewById(R.id.inp_oblika);
            EditText namen=(EditText) findViewById(R.id.inp_namen);
            EditText merska_enota= (EditText) findViewById(R.id.inp_kolicina);
            EditText st_tablet = (EditText) findViewById(R.id.inp_stevilo_tbl);

            poimenovanje.setText(vhod.getPoimenovanje());
            kolicina.setText(vhod.getKolicina().toString());
            oblika.setText(vhod.getOblika());
            namen.setText(vhod.getNamen());
            merska_enota.setText(vhod.getMerska_enota());
            st_tablet.setText(vhod.getSt_tablet().toString());
        }else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Zdravilo ni bilo najdeno! Prosim vnesite podatke ročno.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    // spremeni izgled menija
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dodaj_zdravilo, menu);
        return true;
    }

    // določi kaj se zgodi ob kliku na gumbe v toolbaru
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_izbrisi_zdravilo:
                izbrisiZdravilo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // brisanje zdravila - smetnjak
    public void izbrisiZdravilo() {
        try{
            app.getPodatki().getListZdravil().remove(message);
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        }
       catch(Exception e)
        {
            startActivity(new Intent(activity_Novo_zdravilo.this, activity_Seznam_zdravil_rv.class ));
            e.printStackTrace();
        }




    }
}
