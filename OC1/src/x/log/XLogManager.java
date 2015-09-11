package x.log;

public final class XLogManager
    implements ILogManager
{

    public ILog getLog(Class c) {
        return new XLog(c);
    }
}
