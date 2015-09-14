package x.log;

import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.KeyValuePair;
import x.pagefactories.KeyValuePairListSource;

import java.util.LinkedList;

public final class XLogWriter
        implements KeyValuePairListSource
{

    LinkedList<String> log = new LinkedList<String>();
    
    public static XLogWriter of() {
        return Registry.get(XLogWriter.class);
    }
    
    public void log(String message) {
        System.out.println(message);
        log.add(message);
        if (log.size()>1000) {
            log.removeFirst();
        }
    }

    public String dump() {
        StringBuilder out = new StringBuilder();
        for (String line : log) {
            out.append(line + "\r\n");
        }
        return out.toString();
    }

    @Override
    public LiveList<KeyValuePair> asKeyValuePairs() {
        return XLiveList.of(log);
    }
}
