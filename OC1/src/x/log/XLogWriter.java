package x.log;

import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.pagefactories.KeyValuePair;
import x.pagefactories.KeyValuePairListSource;
import x.util.Translator;

import java.util.LinkedList;

public final class XLogWriter
        implements KeyValuePairListSource
{

    final LinkedList<String> log = new LinkedList<String>();
    final XLiveList published = XLiveList.of(log, new Translator() {
        @Override
        public Object translate(Object o) {
            return new KeyValuePair("" + o,o);
        }
    });

    public static XLogWriter of() {
        return Registry.get(XLogWriter.class);
    }
    
    public void log(String message) {
        System.out.println(message);
        log.add(message);
        if (log.size()>1000) {
            log.removeFirst();
        }
        published.fireChangeEvent();
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
        return published;
    }
}
