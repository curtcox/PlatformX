package oc1.log;

import common.ILog;
import common.ILogManager;
import common.Registry;

public final class LogManager
    implements ILogManager
{

    static LogManager of() {
        return Registry.get(LogManager.class);
    }
    
    public ILog getLog(Class c) {
        return new Log(c);
    }
}
