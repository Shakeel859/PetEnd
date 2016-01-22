package com.example.shakeel.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Shakeel on 13-December-2015.
 */
public class ProvideServices extends Activity {
    WifiManager wifimanager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
    SmsManager smsmanager = SmsManager.getDefault();
    String DefaultNumber = "+923401868185";
    String DefaultNumber2 = "+923315337994";

    Context context;
    String Message;
    public ProvideServices( Context ctx ,String msg ) {
        this.context = ctx;
        this.Message = msg;
        Toast.makeText(context,  msg , Toast.LENGTH_SHORT).show();
    }
    public void Perform_Operation(){

        Toast.makeText(ProvideServices.this, " Perform Operation Method Called", Toast.LENGTH_SHORT).show();


        /*
        switch (Message){
            case "Location":
                Toast.makeText(ProvideServices.this, " Locaiton Case ", Toast.LENGTH_SHORT).show();

                break;

            case "Battery":
                Toast.makeText(ProvideServices.this, " Battery Case ", Toast.LENGTH_SHORT).show();
                break;

            case "Wifi":
                WifiManager wifimanager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

                if(wifimanager.isWifiEnabled()){
                    Toast.makeText(ProvideServices.this, " Wifi is Enabled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ProvideServices.this, " Wifi is Not Enabled", Toast.LENGTH_SHORT).show();
                }
                /*
                SmsManager smsmanager = SmsManager.getDefault();

                if(wifimanager.isWifiEnabled()){

                    smsmanager.sendTextMessage(DefaultNumber,null,"Wifi is Enabled",null,null);
                }
                else
                {
                    smsmanager.sendTextMessage(DefaultNumber,null,"Wifi Is Not  Enabled",null,null);
                }

                break;
                */

        }


    }

