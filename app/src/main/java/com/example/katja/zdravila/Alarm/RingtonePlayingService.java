package com.example.katja.zdravila.Alarm;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.katja.zdravila.R;

/**
 * Created by Katja on 24. 05. 2016.
 */
public class RingtonePlayingService extends Service {
   /*
    private Context context;
    MediaPlayer mMediaPlayer;
    private int startId;*/

    MediaPlayer media_song;
    private boolean isRunning;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("LocalService", "Received start id" + startId + ": " + intent);

        // lovlenje extra stringov
        String stanje = intent.getExtras().getString("extra");

        Log.e("Ringtone stanje:", stanje);


        assert  stanje !=null;
        switch (stanje) {
            case "alarm_on":
                startId = 1;
                break;
            case "alarm_off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId == 1) // zažene alarm
        {
            Log.e("no music", "you want start");
            media_song = MediaPlayer.create(this, R.raw.elegant_ringtone);
            media_song.start();
            this.isRunning = true;


            //notification
            // nastavi notification service
            NotificationManager notify_manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            // nastavi activity, ki se odpre ko se sproži alarm
            Intent intent_pop_up = new Intent(this.getApplicationContext(), activity_alarm_pop_up.class);
            // nastavi pending intent
            PendingIntent pending_intent_pop_up = PendingIntent.getActivity(this, 0, intent_pop_up, 0);


            // nastavi notification parametre
            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Izklopi alarm")
                    .setContentText("Klikni me!")
                    .setContentIntent(pending_intent_pop_up)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ring)
                    .build();

            // nastavi set up start comand
            notify_manager.notify(0,notification_popup);

        }
        else if(this.isRunning && startId == 0) // ustavi alarm
        {
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            Log.e("there is music", "you want end");



        }
        else if (!this.isRunning && startId == 1)
        {
            Log.e("is music", "you want start");
            this.isRunning = true;
        }

            return START_NOT_STICKY;


}

    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        //this.isRunning = false;
    }

}
