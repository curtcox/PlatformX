package c1.services;

import java.util.Date;

public final class Time {
    
    public static String formatted(long timestamp) {
        Date date = new Date(timestamp);
        return date.toString();
    }
}
