package x.log;

public final class XLogManager
    implements ILogManager
{

    public ILog getLog(Object o) {
        return XLog.of(o);
    }
}
