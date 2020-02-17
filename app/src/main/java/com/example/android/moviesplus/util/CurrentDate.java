package com.example.android.moviesplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

    public static String getDate() {
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd ");
        return currentDate.format(new Date());
    }
}
