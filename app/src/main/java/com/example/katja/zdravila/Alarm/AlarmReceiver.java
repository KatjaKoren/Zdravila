package com.example.katja.zdravila.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by Katja on 17. 05. 2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // lovlenje extra stringov iz intenta
        String get_your_string = intent.getExtras().getString("extra");
        Log.e("Kaj je kluč?" , get_your_string);

        // intent do ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // pošlje extra string in main acttivity v ringtoneservice
        service_intent.putExtra("extra", get_your_string);

        // zažene ringtone service
        context.startService(service_intent);


    }
}
