package com.tecnm.version.tres.utils;

import android.content.Context;
import android.widget.Toast;

public class Notify {
    public static void showToast(Context c, String message)
    {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(c, message, duration);
        toast.show();
    }
}
