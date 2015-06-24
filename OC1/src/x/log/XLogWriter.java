package x.log;

import x.Registry;
import java.util.LinkedList;

public final class XLogWriter {

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

}
