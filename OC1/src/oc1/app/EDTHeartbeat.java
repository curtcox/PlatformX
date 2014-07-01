package oc1.app;

import com.codename1.ui.Display;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
public final class EDTHeartbeat
    implements Runnable
{

    final long scheduled = now();
    
    static void start() {
        new EDTHeartbeat().addBeat();
    }

    private static Display getDisplay() {
        return Registry.get(Display.class);
    }

    void addBeat() {
        getDisplay().callSerially(new EDTHeartbeat());
    }
    
    public void run() {
        long executed = now();
        long duration = executed - scheduled;
        if (duration>100) {
            log("scheduled=" + scheduled + " executed=" + executed + " duration=" + duration);
        }
        addBeat();
    }
    
    private static long now() {
        return System.currentTimeMillis();
    }
    
    private void log(String message) {
        LogManager.of().getLog(EDTHeartbeat.class).log(message);    
    }

}
