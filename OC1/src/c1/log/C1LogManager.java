package c1.log;

import common.log.ILog;
import common.log.ILogManager;
import common.Registry;

public final class C1LogManager
    implements ILogManager
{

    static C1LogManager of() {
        return Registry.get(C1LogManager.class);
    }
    
    public ILog getLog(Class c) {
        return new C1Log(c);
    }
}
