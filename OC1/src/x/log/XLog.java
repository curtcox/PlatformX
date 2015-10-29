package x.log;

import java.util.logging.Logger;

public final class XLog
    implements ILog
{
    final Object target;
    final Class clazz;
    final String name;
    final String prefix;
    final Logger log;
    
    private XLog(Object target) {
        this.target = target;
        this.clazz = target instanceof Class ? (Class) target : target.getClass();
        name = clazz.getCanonicalName();
        prefix = ":" + name + ":";
        log = Logger.getLogger(name);
    }

    public static XLog of(Object target) {
        return new XLog(target);
    }

    public void log(Throwable e) {
        log("Caught exception");
        log("class=" + e.getClass());
        log("message=" + e.getMessage());
        log.info(e.getMessage());
        e.printStackTrace();
    }

    public void log(String message, Object... details) {
        //log.info(message);
        getLogWriter().log(target,clazz,message,details);
    }

    private XLogWriter getLogWriter() {
        return XLogWriter.of();
    }

}
