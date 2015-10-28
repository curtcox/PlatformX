package c1.app;

import com.codename1.ui.Display;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class HeartbeatMonitor
    extends Thread
{
    private Heartbeat heartbeat;
    
    HeartbeatMonitor() {
        super("Monitor");    
    }
    
    static void install() {
        new HeartbeatMonitor().start();
    }
    
    @Override
    public void run() {
        for (;;) {
           enqueueHeartbeat();
           waitForHeartbeatToFinish();
           sleepSeconds(5);
        }
    }

    private Display getDisplay() {
        return Registry.get(Display.class);
    }

    void enqueueHeartbeat() {
        heartbeat = new Heartbeat();
        getDisplay().callSerially(heartbeat);
    }

    private void waitForHeartbeatToFinish() {
        int duration = 1;
        int total = duration;
        sleepSeconds(duration);
        while (!heartbeat.executed()) {
            log("Heartbeat not finished after " + total + " seconds ");
            duration *= 2;
            sleepSeconds(duration);
            total += duration;
        }
    }

    private void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log(e);
        }
    }
    
    private void log(String message) {
        getLog().log(message);
    }

    private void log(Throwable t) {
        getLog().log(t);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(HeartbeatMonitor.class,this);
    }

}
