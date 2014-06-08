package myapp.log;

import myapp.Registry;
import java.util.LinkedList;

/**
 *
 * @author Curt
 */
public final class LogWriter {

    LinkedList<String> log = new LinkedList<String>();
    
    public static LogWriter of() {
        return Registry.get(LogWriter.class);
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
            out.append(line + "/r/n");
        }
        return out.toString();
    }

}
