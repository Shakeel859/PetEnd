package com.example.shakeel.myapplication;

import android.app.Application;

/**
 * Created by Bilal on 1/26/2016.
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    MyApplication(){};

    private boolean running;

    public boolean getRunning() {
        return this.running;
    }

    public void setRunning(boolean someVariable) {
        this.running = someVariable;
    }

    public static synchronized MyApplication getInstance(){
        if(instance==null){
            instance=new MyApplication();
        }
        return instance;
    }}