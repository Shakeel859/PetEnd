package com.example.shakeel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by Bilal on 1/27/2016.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Main_Number_EditText;
    EditText Emergency_Number_Text;
    String Main_Number;
    String Emergency_Number;
    String get_Main;
    String get_Emergency;
   // GetNumber o1= new GetNumber();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings_main);


                Main_Number_EditText = (EditText) findViewById(R.id.main_number);
                Emergency_Number_Text = (EditText) findViewById(R.id.emer_number);

            SharedPreferences sharedPreferences = getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Map<String, ?> allEntries = sharedPreferences.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                if (entry.getKey().toString().equals("Main Number")) {
                    get_Main = entry.getValue().toString();
                    Main_Number_EditText.setText(get_Main);


                }

                if (entry.getKey().toString().equals("Emergency Number")) {

                    get_Emergency = entry.getValue().toString();
                    Emergency_Number_Text.setText(get_Emergency);
                }
                Button Save = (Button) findViewById(R.id.save);
                Save.setOnClickListener(this);


            }
        }




    @Override
    public void onClick(View v) {
        Main_Number = Main_Number_EditText.getText().toString();
        String Main_Number_Key = "Main Number";
        String Emergency_Number_Key = "Emergency Number";
        Emergency_Number = Emergency_Number_Text.getText().toString();
        SharedPreferences sharedPreferences1 = getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString(Main_Number_Key, Main_Number);
        editor1.putString(Emergency_Number_Key, Emergency_Number);


        editor1.commit();
        Toast.makeText(this, " Number is saved Successfully", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Main_Number_Key + " :  "  + Main_Number, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Emergency_Number_Key + " : "  + Emergency_Number, Toast.LENGTH_SHORT).show();

    }


    public String Pass_Main_Number(){
    return get_Main;}
    public String Pass_Emergency_Number(){

        return get_Emergency;
    }

};

