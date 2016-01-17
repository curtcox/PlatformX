package x.log;

import x.app.Registry;
import x.event.LiveList;
import x.event.XLiveList;

import java.util.Arrays;
import java.util.LinkedList;

public final class XLogWriter
    implements LiveList.Source
{

    final LinkedList<XLogEntry> log = new LinkedList<XLogEntry>();
    final XLiveList published = XLiveList.of(log);

    public static XLogWriter of() {
        return Registry.get(XLogWriter.class);
    }
    
    public void log(Object target, Class clazz,String message,Object...details) {
        sysout(target,clazz,message,details);
        log.add(XLogEntry.of(target,clazz,message,details));
        if (log.size()>1000) {
            log.removeFirst();
        }
        published.fireChangeEvent();
    }

    void sysout(Object target, Class clazz,String message,Object...details) {
        if (details.length == 0) {
            System.out.println(message);
        } else if (details.length == 1) {
            System.out.println(message + details[0]);
        } else {
            System.out.println(message + Arrays.asList(details));
        }
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

    @Override
    public LiveList asLiveList() {
        return published;
    }
}
