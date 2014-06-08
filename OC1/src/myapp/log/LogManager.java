package myapp.log;

import myapp.Registry;

/**
 *
 * @author Curt
 */
public final class LogManager {

    public static LogManager of() {
        return Registry.get(LogManager.class);
    }
    
    public Log getLog(Class c) {
        return new Log(c);
    }
}
