package com.example.shakeel.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.shakeel.myapplication.MainActivity.*;

/**
 * Created by Shakeel on 13-December-2015.
 */
// this is ne wcooment


public class CatchMessageClass extends BroadcastReceiver {

    String DefaultNumber = "+923401868185";
    String DefaultNumber2 = "+923315337994";
    Context ctx ;
    MyApplication g1 = MyApplication.getInstance();


    String Main_Number;
    String Emgergency_Number;
    boolean msg_from_main_number = false;
    boolean msg_from_emergency_number = false;





    @Override
    public void onReceive(Context context, Intent intent) {

       // SettingsActivity obj = new SettingsActivity();
         SharedPreferences sharedPreferences = context.getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
           Main_Number = sharedPreferences.getString("Main Number", null);
          Emgergency_Number = sharedPreferences.getString("Emergency Number",null);




       // final
      // final  String Emergency_Number = obj.Pass_Emergency_Number();


        //Intent intent1 = new Intent(this,SettingsActivity.class);

       // Toast.makeText(CatchMessageClass.this, "", Toast.LENGTH_SHORT).show();

      //  SharedPreferences sharedPreferences = CatchMessageClass.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);

      //  GetNumber o1= new GetNumber();
      //  String A = o1.GetNumber1();
       // String B = o1.GetNumber2();
       // String s1 = o1.GNumber().toString();
       boolean s = g1.getRunning();
        if (s == true) {
          //  ProvideServices obj;

            this.ctx = context;

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
                ShowNotification showNotification1 = new ShowNotification();
               // showNotification1.ViaToast(context,number);
                showNotification1.ViaToast(context,number);
                showNotification1.ViaToast(context,  " Main" + Main_Number   + " Emergency "+ Emgergency_Number);


                if (number.equals(Main_Number) || number.equals(Emgergency_Number))
                {
                    if(number.equals(Main_Number)){
                        msg_from_main_number = true;
                        ShowNotification showNotification = new ShowNotification();
                        showNotification.ViaToast(context,number);

                    }
                    if(number.equals(Emgergency_Number)){

                       msg_from_emergency_number = true;
                        ShowNotification showNotification = new ShowNotification();
                        showNotification.ViaToast(context,number);
                    }


                    switch (messagebody) {


                        case "Location":
                            final GPSTracker gps;
                            gps = new GPSTracker(context);
                            Toast.makeText(context, "Location Requested", Toast.LENGTH_LONG).show();
                            if (gps.canGetLocation()) {
                                final double latitude = gps.getLatitude();
                                final double longitude = gps.getLongitude();
                                new CountDownTimer(15000,1000) {
                                    public void onTick(long millisUntilFinished) {
                                        // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);

                                    }

                                    public void onFinish() {
                                        String s1 = Double.toString(latitude);
                                        String s2= Double.toString(longitude);
                                        String s3 = s1 + "," +s2;
                                        SmsManager smsmanager = SmsManager.getDefault();
                                        if(msg_from_main_number){
                                        smsmanager.sendTextMessage(Main_Number, null, s3, null, null);}
                                        else if (msg_from_emergency_number){
                                            smsmanager.sendTextMessage(Emgergency_Number, null, s3, null, null);

                                        }
                                        gps.stopUsingGPS();
                                    }
                                }.start();
                               /* if (latitude == 0 && longitude == 0) {
                                    Toast.makeText(
                                            context,
                                            "Trying to get GPS..."
                                            , Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(
                                            context,
                                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                                    + longitude, Toast.LENGTH_LONG).show();
                                }*/
                            }





                            break;
                        case "GPS status":
                            final GPSTracker gps2;
                            gps2 = new GPSTracker(context);
                            SmsManager smsmanager3 = SmsManager.getDefault();

                            if(gps2.gpsstatus())
                               // smsmanager3.sendTextMessage(DefaultNumber2, null,"GPS Enabled", null, null);
                            {
                                if (msg_from_main_number) {
                                    smsmanager3.sendTextMessage(Main_Number, null, "GPS Enabled", null, null);
                                } else if (msg_from_emergency_number) {
                                    smsmanager3.sendTextMessage(Emgergency_Number, null, "GPS Enabled", null, null);

                                }
                            }

                            else
                            {    //smsmanager3.sendTextMessage(DefaultNumber2, null,"GPS Disabled", null, null);
                            if (msg_from_main_number) {
                                smsmanager3.sendTextMessage(Main_Number, null, "GPS Disabled", null, null);
                            } else if (msg_from_emergency_number) {
                                smsmanager3.sendTextMessage(Emgergency_Number, null, "GPS Disabled", null, null);

                            }}
                            break;
                        case "GPS On":
                          //  final GPSTracker gps2;
                           // gps2 = new GPSTracker(context);
                          //  boolean a= gps2.gpsstatus();
                        case "Battery":
                            Intent batteryIntent = context.getApplicationContext().registerReceiver(null,
                                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                            Intent batteryIntent1 = context.getApplicationContext().registerReceiver(null,
                                    new IntentFilter(Intent.ACTION_BATTERY_LOW));



                            int rawlevel = batteryIntent.getIntExtra("level", -1);
                            double scale = batteryIntent.getIntExtra("scale", -1);
                            double level = -1;
                            if (rawlevel >= 0 && scale > 0) {
                                level = rawlevel / scale;
                            }
                            level=level*100;
                            String s1 = Double.toString(level);
                            s1 = "Battery level is "+s1+"%";
                            SmsManager smsmanager = SmsManager.getDefault();
                           // smsmanager.sendTextMessage(DefaultNumber2, null, s1, null, null);
                            if (msg_from_main_number) {
                                smsmanager.sendTextMessage(Main_Number, null, s1, null, null);
                            } else if (msg_from_emergency_number) {
                                smsmanager.sendTextMessage(Emgergency_Number, null, s1, null, null);

                            }




                            //batteryLevel();
                            Toast.makeText(context, "Battery Level is  Requested", Toast.LENGTH_LONG).show();
                            Toast.makeText(context, s1, LENGTH_SHORT).show();
                            break;
                        case "Wifi status":

                            Toast.makeText(context, "Wifi Status is requested", Toast.LENGTH_LONG).show();
                            Wifi w1 = new Wifi();
                            w1.WifiCheck();
                            break;
                        case "Wifi on":
                            Wifi w2 = new Wifi();
                            w2.Wifi_on();
                            break;
                        case "Wifi off":
                            Wifi w3 = new Wifi();
                            w3.Wifi_off();
                            break;
                        case "Data on":
                            enableMobileData(ctx, true);
                            //Toast.makeText(context, "Wifi turned off", Toast.LENGTH_LONG).show();
                            break;
                        case "Data off":
                            enableMobileData(ctx,false);
                            //Toast.makeText(context, "Wifi turned off", Toast.LENGTH_LONG).show();
                            break;
                        case "Data status":


                            boolean mobileYN;
                            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
                                mobileYN = Settings.Global.getInt(context.getContentResolver(), "mobile_data", 1) == 1;
                            else
                                mobileYN = Settings.Secure.getInt(context.getContentResolver(), "mobile_data", 1) == 1;

                                SmsManager smsmanager2 = SmsManager.getDefault();
                                if (mobileYN == false)
                                {      //smsmanager2.sendTextMessage(DefaultNumber2, null, "Dats is Off", null, null);
                            if (msg_from_main_number) {
                                smsmanager2.sendTextMessage(Main_Number, null, "Dats is Off", null, null);
                            } else if (msg_from_emergency_number) {
                                smsmanager2.sendTextMessage(Emgergency_Number, null, "Dats is Off", null, null);

                            }}


                                else
                                {  // smsmanager2.sendTextMessage(DefaultNumber2, null, "Data is On", null, null);
                            if (msg_from_main_number) {
                                smsmanager2.sendTextMessage(Main_Number, null, "Dats is On", null, null);
                            } else if (msg_from_emergency_number) {
                                smsmanager2.sendTextMessage(Emgergency_Number, null, "Dats is On", null, null);

                            }}

                            break;

                        default:
                            //Toast.makeText(context, "Some Random Message is sent", Toast.LENGTH_LONG).show();
                            break;

                    }

                } else {
                   // Toast.makeText(context, " None of Our Business \n " + number + "\n" + messagebody, Toast.LENGTH_LONG).show();

                }

            }

        }
        else
        {
            SmsManager smsmanager2 = SmsManager.getDefault();
           // smsmanager2.sendTextMessage(DefaultNumber2, null, " Pet End Is Off ... ", null, null);

            if (msg_from_main_number) {
                smsmanager2.sendTextMessage(Main_Number, null, " Pet End Is Off ... ", null, null);
            } else if (msg_from_emergency_number) {
                smsmanager2.sendTextMessage(Emgergency_Number, null, " Pet End Is Off ... ", null, null);

            }


        }
   }

    public class Wifi{

        WifiManager wifimanager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        SmsManager smsmanager = SmsManager.getDefault();
    public void WifiCheck() {
        if (wifimanager.isWifiEnabled()) {

            //smsmanager.sendTextMessage(DefaultNumber2, null, "Wifi is Enabled", null, null);
            if (msg_from_main_number) {
                smsmanager.sendTextMessage(Main_Number, null, "Wifi is Enabled", null, null);
            } else if (msg_from_emergency_number) {
                smsmanager.sendTextMessage(Emgergency_Number, null, "Wifi is Enabled", null, null);

            }



        } else {
           // smsmanager.sendTextMessage(DefaultNumber2, null, "Wifi Is Not Enabled", null, null);

            if (msg_from_main_number) {
                smsmanager.sendTextMessage(Main_Number, null, "Wifi is Not Enabled", null, null);
            } else if (msg_from_emergency_number) {
                smsmanager.sendTextMessage(Emgergency_Number, null, "Wifi is Not Enabled", null, null);

            }

        }
    }
    public void Wifi_on(){
        wifimanager.setWifiEnabled(true);
    }
        public void Wifi_off(){
            wifimanager.setWifiEnabled(false);
        }

    }



    private static boolean enableMobileData(Context context, boolean enable) {
        boolean bool = true;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Boolean.TYPE;
            Method enableDataMethod = ConnectivityManager.class.getMethod("setMobileDataEnabled", arrayOfClass);
            //DailySchedulerLog.v("Method enableDataMethod = %s", enableDataMethod.getName());
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Boolean.valueOf(enable);
            enableDataMethod.invoke(cm, arrayOfObject);
            return bool;
        } catch (Exception localException) {
            //            while (true) {
           // DailySchedulerLog.d("Exception !!!!!!!!! loops");
            return bool = false;
            //            }
        }
    }
}
;
















