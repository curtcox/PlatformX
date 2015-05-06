package c1.app;

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public final class Heartbeat
    implements Runnable
{
    private long executed;
    private long duration;
    private final long scheduled = now();
    
    synchronized public void run() {
        executed = now();
        duration = executed - scheduled;
        if (duration>100) {
            log();
        }
    }
    
    private static long now() {
        return System.currentTimeMillis();
    }
    
    private void log(String message) {
        getLog().log(message);
    }

    private void log() {
        log("scheduled=" + scheduled + " executed=" + executed + " duration=" + duration);
    }

    synchronized boolean executed() {
        return executed != 0;
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Heartbeat.class);
    }

}
