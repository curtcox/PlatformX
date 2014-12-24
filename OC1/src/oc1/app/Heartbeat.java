package oc1.app;

import common.ILog;
import common.ILogManager;
import common.Registry;
import oc1.log.LogManager;

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
