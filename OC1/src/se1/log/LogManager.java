package se1.log;

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public final class LogManager
    implements ILogManager
{

    public static LogManager of() {
        return Registry.get(LogManager.class);
    }
    
    public ILog getLog(Class c) {
        return new Log(c);
    }
}
