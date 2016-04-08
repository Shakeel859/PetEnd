package com.example.shakeel.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Shakeel on 08-April-2016.
 */
public class ShowNotification {

    Context context ;

    public void ViaToast(Context ctx , String message ){

        this.context = ctx;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
