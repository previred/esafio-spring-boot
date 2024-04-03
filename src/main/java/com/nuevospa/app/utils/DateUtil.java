package com.nuevospa.app.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public final class DateUtil {
    public static String formatToChileanDate(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

}