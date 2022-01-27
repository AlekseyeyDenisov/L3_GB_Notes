package ru.dw.gbnotes.utils;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static void systemToast(Context context, int id) {
        Toast.makeText(
                context,
                context.getString(id),
                Toast.LENGTH_SHORT).show();
    }
}
