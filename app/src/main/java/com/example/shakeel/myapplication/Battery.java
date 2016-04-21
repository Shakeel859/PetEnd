package com.example.shakeel.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;


/**
 * Created by Bilal on 4/15/2016.
 */

public class Battery extends BroadcastReceiver {
    int pre_battery = 0;
    String Main_Number;
    private final static String BATTERY_LEVEL = "level";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BATTERY_LEVEL, 0);


       if (level<16)
        {
            if (level%3==0)
            {
        if (pre_battery != level) {
                  /*  Notification mNotification = new Notification.Builder(context)

                            .setContentTitle("Batter Warning")
                            .setContentText("Battery level at :"+level+"%")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .build();
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, mNotification);*/
            SharedPreferences sharedPreferences = context.getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
            Main_Number = sharedPreferences.getString("Main Number", null);
            SmsManager smsmanager = SmsManager.getDefault();
            String batt_level = Integer.toString(level);
            smsmanager.sendTextMessage(Main_Number, null, batt_level, null, null);
            pre_battery = level;
      // }
         }
         }
        }
    }
}