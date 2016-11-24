package com.example.katja.zdravila.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.katja.zdravila.R;

public class activity_alarm_pop_up extends AppCompatActivity {

    Button izklopi;
    Context context;
    AlarmManager alarm_manager;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         /*  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alarm_pop_up);

       */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_alarm_pop_up);
        this.context = this;

        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);

        //inicializiramo alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // izklopi alarm
        izklopi = (Button) findViewById(R.id.btnOff);
        izklopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // doda extra string v my_intent, pove alarmu, da se ugasne
                my_intent.putExtra("extra", "alarm_off");

                alarm_manager.cancel(pending_intent); // alarm off

                // ustavi zvonenje
                sendBroadcast(my_intent);

            }
        });




    }




}
