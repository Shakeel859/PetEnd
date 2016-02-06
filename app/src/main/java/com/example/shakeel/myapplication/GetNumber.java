package com.example.shakeel.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bilal on 1/27/2016.
 */
public class GetNumber extends AppCompatActivity {
    String Text1;
    String Text2;
     String GetNumber1() {
      SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        Text1 = sharedPreferences.getString(getString(R.string.main_string),"");
        return Text1;
}
     String GetNumber2() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        Text2 = sharedPreferences.getString(getString(R.string.emergency_string),"");
        return Text2;
    }



}
