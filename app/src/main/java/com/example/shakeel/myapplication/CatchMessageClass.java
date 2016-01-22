package com.example.shakeel.myapplication;

//h
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Shakeel on 13-December-2015.
 */
public class CatchMessageClass extends BroadcastReceiver {
    String DefaultNumber = "+923401868185";
    String DefaultNumber2 = "+923315337994";
    Context ctx ;
    @Override
    public void onReceive(Context context, Intent intent) {
        ProvideServices obj;

        this.ctx  = context;

        String number = null;
        String messagebody = null;
        StringBuilder buf = new StringBuilder();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (int i = 0; i < messages.length; i++) {
                SmsMessage message = messages[i];
                buf.append("Received SMS from  ");
                number = message.getDisplayOriginatingAddress();
                messagebody = message.getDisplayMessageBody();
            }
           if(number.equals(DefaultNumber)||number.equals(DefaultNumber2)){

               switch(messagebody){

                   case "Location":
                       Toast.makeText(context, "Location Requested", Toast.LENGTH_LONG).show();




                       break;
                   case "Battery":

                       //batteryLevel();
                       Toast.makeText(context, "Battery Level is  Requested", Toast.LENGTH_LONG).show();

                       break;
                   case "Wifi":

                       Toast.makeText(context, "Wifi Status is requested", Toast.LENGTH_LONG).show();
                       WifiMethod();
                       break;


                    default:
                        Toast.makeText(context, "Some Random Message is sent", Toast.LENGTH_LONG).show();
                       break;

               }

           }
            else
           {
               Toast.makeText(context, " None of Our Business \n " + number  + "\n" + messagebody, Toast.LENGTH_LONG).show();
           }

        }

    }
    public void WifiMethod() {
        WifiManager wifimanager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);

        SmsManager smsmanager = SmsManager.getDefault();

        if(wifimanager.isWifiEnabled()){

            smsmanager.sendTextMessage(DefaultNumber, null, "Wifi is Enabled", null, null);
        }
        else
        {
            smsmanager.sendTextMessage(DefaultNumber, null, "Wifi Is Not Enabled", null, null);
        }
    }



    }










