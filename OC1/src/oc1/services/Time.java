package oc1.services;

import java.util.Date;

/**
 *
 * @author Curt
 */
public final class Time {
    
    public static String formatted(long timestamp) {
        Date date = new Date(timestamp);
        return date.toString();
    }
}