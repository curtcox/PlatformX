package x.log;

import x.Registry;

public final class CommonLogManager
    implements ILogManager
{

    public static CommonLogManager of() {
        return Registry.get(CommonLogManager.class);
    }
    
    public ILog getLog(Class c) {
        return new CommonLog(c);
    }
}
