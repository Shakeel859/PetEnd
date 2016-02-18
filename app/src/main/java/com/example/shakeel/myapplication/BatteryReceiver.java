package com.example.shakeel.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Bilal on 2/16/2016.
 */
public class BatteryReceiver extends BroadcastReceiver {
// from https://stackoverflow.com/questions/27902031/execute-a-piece-of-code-on-certain-battery-level-in-android
    @Override
    public void onReceive(Context context, Intent intent) {

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        if (level < 99) {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage("+923315337994", null, "Battery changed", null, null);

        }
    }

}