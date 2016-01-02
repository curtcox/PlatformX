package x.services;

import java.util.Date;

public final class XTime {
    
    public static String formatted(long timestamp) {
        Date date = new Date(timestamp);
        return date.toString();
    }
}
