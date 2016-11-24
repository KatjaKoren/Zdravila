package com.example.katja.zdravila;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.DataAll;
import com.example.Zdravilo;
import com.example.katja.zdravila.Alarm.AlarmReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class activity_Dodaj_opomnik extends AppCompatActivity {

    //alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    TextView prikaz;
    FloatingActionButton potrdi;
    PendingIntent pending_intent;
    Boolean on_off = false;
    String string_ura;
    String string_minute;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__dodaj_opomnik);
        this.context = this;

        // Intent do AlarmReceiver classa
        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);
        my_intent.setAction("com.example.katja.zdravila.ACTION_NOTIFICATION_START");

        //inicializiramo alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //inicializiramo timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePickerZdravila);

        final Calendar koledar = Calendar.getInstance();

        // prikaže trenutni nastavljen čas
        prikaz = (TextView) findViewById(R.id.tvAlarm);


        //alarm on / off
        Switch alarm_switch = (Switch) findViewById(R.id.switchAlarm);
        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked== true) {
                    koledar.set(koledar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                    koledar.set(koledar.MINUTE, alarm_timepicker.getCurrentMinute());

                    int ura = alarm_timepicker.getCurrentHour();
                    int minute = alarm_timepicker.getCurrentMinute();

                    string_ura = String.valueOf(ura);
                    string_minute = String.valueOf(minute);

                    on_off = true;
                }

                else if (isChecked == false)
                {
                    // doda extra string v my_intent, pove alarmu, da se ugasne
                    my_intent.putExtra("extra", "alarm_off");

                    alarm_manager.cancel(pending_intent); // alarm off

                    // ustavi zvonenje
                    sendBroadcast(my_intent);

                    on_off = false;
                }

            }
        });

   /*     potrdi = (Button) findViewById(R.id.btnPotrdi);
        potrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (on_off == true)
                {

                    // doda extra string v my_intent, pove alarmu, da se prižge
                    my_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(activity_Dodaj_opomnik.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // set AlarmManager
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, koledar.getTimeInMillis(), pending_intent); // alarm on
                    on_off = false;

                    // vrne se na začetni activity
                    startActivity(new Intent(activity_Dodaj_opomnik.this, activity_Main.class));

                    // prikaže toast kdaj je nastavljen alarm
                    Context context = getApplicationContext();
                    CharSequence text = "Alarm: " + string_ura + ":" +string_minute;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                if(on_off == false)
                {
                    // vrne se na začetni activity
                    startActivity(new Intent(activity_Dodaj_opomnik.this, activity_Main.class));
                }


            }
        });*/


        potrdi = (FloatingActionButton) findViewById(R.id.fbtnPotrdi);
        potrdi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (on_off == true)
                {

                    // doda extra string v my_intent, pove alarmu, da se prižge
                    my_intent.putExtra("extra", "alarm_on");

                    pending_intent = PendingIntent.getBroadcast(activity_Dodaj_opomnik.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // set AlarmManager
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, koledar.getTimeInMillis(), pending_intent); // alarm on
                    on_off = false;

                    // vrne se na začetni activity
                    startActivity(new Intent(activity_Dodaj_opomnik.this, activity_Main.class));

                    // prikaže toast kdaj je nastavljen alarm
                    Context context = getApplicationContext();
                    CharSequence text = "Alarm: " + string_ura + ":" +string_minute;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                if(on_off == false)
                {
                    // vrne se na začetni activity
                    startActivity(new Intent(activity_Dodaj_opomnik.this, activity_Main.class));
                }
            }
        });

        // gumb za dodajanje novih zdravil
        ImageButton imgBtnDodaj = (ImageButton) findViewById(R.id.imgBtnDodaj);
        imgBtnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_Dodaj_opomnik.this, activity_Novo_zdravilo.class));
            }
        });

        // 24 urni pogled
        TimePicker picker = (TimePicker) findViewById(R.id.timePickerZdravila);
        picker.setIs24HourView(true);
        picker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        // spiner s seznamom zdravil
        DataAll data = DataAll.getSeznamZdravilData();
        List<String> spinnerArray =  new ArrayList<String>();
        for(Zdravilo z: data.getListZdravil())
            spinnerArray.add(z.getPoimenovanje());
        Spinner spinnerZdravila = (Spinner)findViewById(R.id.spinnerZdravila);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZdravila.setAdapter(adapter);

    }

}
