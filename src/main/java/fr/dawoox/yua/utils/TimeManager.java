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
        long diffInMilmies = Math.abs(Date.from(second).getTime() - Date.from(first).getTime());
        long diff = TimeUnit.DAYS.convert(diffInMilmies, TimeUnit.MILLISECONDS);
        return diff;
    }
}
