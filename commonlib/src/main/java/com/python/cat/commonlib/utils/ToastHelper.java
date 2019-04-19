package com.python.cat.commonlib.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

public class ToastHelper {

    private ToastHelper() {
    }

    private static Toast toast;

    public static void show(Context context, CharSequence sequence, int time) {
        cancel();
        if (toast == null) {
            toast = Toast.makeText(context, sequence, time);
            toast.show();
        } else {
            toast.show();
        }
    }

    public static void show(Context context, CharSequence sequence) {
        cancel();
        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.show();
        }
    }

    public static void show(Context context, @StringRes int sequence, int time) {

        cancel();
        if (toast == null) {
            toast = Toast.makeText(context, sequence, time);
            toast.show();
        } else {
            toast.show();
        }
    }

    public static void show(Context context, @StringRes int sequence) {
        cancel();
        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.show();
        }
    }


    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
        toast = null;
    }
}
