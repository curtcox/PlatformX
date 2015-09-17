package x.log;

import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.NamedValue;
import x.pagefactories.NamedValueListSource;
import x.util.Translator;

import java.util.LinkedList;

public final class XLogWriter
        implements NamedValueListSource
{

    final LinkedList<XLogEntry> log = new LinkedList<XLogEntry>();
    final XLiveList published = XLiveList.of(log, new Translator() {
        @Override
        public Object translate(Object o) {
            XLogEntry entry = (XLogEntry) o;
            return new NamedValue(entry.time(),entry.message);
        }
    });

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

    @Override
    public LiveList<NamedValue> asNamedValues() {
        return published;
    }
}
