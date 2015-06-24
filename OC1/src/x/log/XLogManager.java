package x.log;

import x.Registry;

public final class XLogManager
    implements ILogManager
{

    public static XLogManager of() {
        return Registry.get(XLogManager.class);
    }
    
    public ILog getLog(Class c) {
        return new XLog(c);
    }
}
