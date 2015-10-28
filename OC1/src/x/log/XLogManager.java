package x.log;

public final class XLogManager
    implements ILogManager
{

    public ILog getLog(Class c, Object o) {
        return XLog.of(o);
    }
}
