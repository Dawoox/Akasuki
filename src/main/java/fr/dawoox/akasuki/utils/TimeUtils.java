package fr.dawoox.akasuki.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Util class for Time Manager
 * @author Dawoox
 * @version 1.2.0
 */
public class TimeUtils {

    /**
     * Return a String with a date formate inside from a Instant
     * @param instant
     * Any Instant
     * @return String
     * String with a date format into
     * @since 1.0.0
     */
    public static String format(Instant instant){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withLocale(Locale.FRANCE).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    /**
     * Returns a difference between two Instants in a long in days
     * @param first
     * First Instant
     * @param second
     * Second Instant to compare to the first
     * @return long
     * Return the long with the difference into
     * @since 1.1.0
     */
    public static long diffInDays(Instant first, Instant second){
        return TimeUnit.DAYS.convert(diffInMillis(first, second), TimeUnit.MILLISECONDS);
    }

    /**
     * Returns a difference between two Instants in a long in Milliseconds
     * @param first
     * First Instant
     * @param second
     * Second Instant to compare to the first
     * @return long
     * Return the long with the difference into
     * @since 1.2.0
     */
    public static long diffInMillis(Instant first, Instant second){
        return Math.abs(Date.from(second).getTime() - Date.from(first).getTime());
    }
}
