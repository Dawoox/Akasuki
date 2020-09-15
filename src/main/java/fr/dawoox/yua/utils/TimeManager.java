package fr.dawoox.yua.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeManager {

    public static String format(Instant instant){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withLocale(Locale.FRANCE).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static long diffInDays(Instant first, Instant second){
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis(first, second), TimeUnit.MILLISECONDS);
        return diffInDays;
    }

    public static long diffInMillis(Instant first, Instant second){
        long diffInMillies = Math.abs(Date.from(second).getTime() - Date.from(first).getTime());
        return diffInMillies;
    }
}
