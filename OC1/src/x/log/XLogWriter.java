package x.log;

import x.app.Registry;
import x.event.LiveList;
import x.event.XLiveList;

import java.util.LinkedList;

public final class XLogWriter {

    final LinkedList<XLogEntry> log = new LinkedList<XLogEntry>();
    final XLiveList published = XLiveList.of(log);

    public static XLogWriter of() {
        return Registry.get(XLogWriter.class);
    }
    
    public void log(Class clazz,String message) {
        System.out.println(message);
        log.add(XLogEntry.of(clazz,message));
        if (log.size()>1000) {
            log.removeFirst();
        }
        published.fireChangeEvent();
    }

    public String dump() {
        StringBuilder out = new StringBuilder();
        for (XLogEntry line : log) {
            out.append(line + "\r\n");
        }
        return out.toString();
    }

    public LiveList<XLogEntry> log() {
        return published;
    }
}
