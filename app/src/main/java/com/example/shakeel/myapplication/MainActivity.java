package com.example.shakeel.myapplication;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mOn = (Button)findViewById(R.id.on);
        Button mOff = (Button)findViewById(R.id.off);
      //  final Button mCheck = (Button)findViewById(R.id.check);
       final MyApplication g1 = MyApplication.getInstance();

        boolean s = g1.getRunning();
        String str = Boolean.toString(s);
        final TextView textView = (TextView) findViewById(R.id.textView);
        if (str =="true")
            textView.setText("Pet Tracking is ON ");
            else
            textView.setText("Pet Tracking is OFF");
       // MyApplication m1 = new MyApplication();


            mOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    g1.setRunning(true);
                    Toast.makeText(MainActivity.this, "Pet Tracking is turned on", Toast.LENGTH_SHORT).show();
                  //  TextView textView = (TextView) findViewById(R.id.textView);
                    boolean s = g1.getRunning();
                    String str = Boolean.toString(s);
                    if (str=="true")
                        textView.setText("Pet Tracking is ON ");
                    else
                        textView.setText("Pet Tracking is OFF");

                }
        });
        mOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               g1.setRunning(false);
                Toast.makeText(MainActivity.this, "Pet Tracking is turned off", Toast.LENGTH_SHORT).show();
               // TextView textView = (TextView) findViewById(R.id.textView);
                boolean s = g1.getRunning();
                String str = Boolean.toString(s);
                if (str=="true")
                    textView.setText("Pet Tracking is ON ");
                else
                    textView.setText("Pet Tracking is OFF");

            }
        });
       /* mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             boolean s = g1.getRunning();
               String str = Boolean.toString(s);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
    }*/
        ;}



      /*  public void onClick(View v) {
            switch (v.getId()) {
                case R.id.on: {
                    Toast.makeText(MainActivity.this, "str", Toast.LENGTH_SHORT).show();
                  //  ((MyApplication) this.getApplication()).setRunning(true);
                    break;
                }

                case R.id.off: {
                  //  ((MyApplication) this.getApplication()).setRunning(false);
                    break;
                }

                case R.id.check: {
                 //   boolean s = ((MyApplication) this.getApplication()).getRunning();
                   // String str = Boolean.toString(s);
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    break;
                }
                //.... etc
            }
        }*/


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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

};
