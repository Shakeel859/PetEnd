package com.example.shakeel.myapplication;

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

/**
 * Created by Bilal on 1/27/2016.
 */
public class SettingsActivity extends AppCompatActivity {
    EditText Main_text;
    EditText Emer_text;
    String Text1;
    String Text2;
   // GetNumber o1= new GetNumber();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings_main);


            Main_text = (EditText) findViewById(R.id.main_text);
            Emer_text = (EditText) findViewById(R.id.emer_text);
            Button Save = (Button) findViewById(R.id.save);

            //String A = o1.GetNumber1();
            //String B = o1.GetNumber2();
            SharedPreferences sharedPreferences = SettingsActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
            Text1 = sharedPreferences.getString(getString(R.string.main_string), "");
            Text2 = sharedPreferences.getString(getString(R.string.emergency_string), "");

            Main_text.setText(Text1);
            Emer_text.setText(Text2);
            Save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = SettingsActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.main_string), Main_text.getText().toString());
                    editor.putString(getString(R.string.emergency_string), Emer_text.getText().toString());
                    editor.commit();
                }
            });



        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {

            }

            return super.onOptionsItemSelected(item);
        }

    };

