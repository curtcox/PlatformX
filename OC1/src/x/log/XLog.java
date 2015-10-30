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
        this.clazz = clazz(target);
        name = name(target);
        prefix = ":" + name + ":";
        log = log(name);
    }

    public static XLog of(Object target) {
        return new XLog(target);
    }

    static Class clazz(Object target) {
        if (target==null) {
            return null;
        }
        return target instanceof Class ? (Class) target : target.getClass();
    }

    static String name(Object target) {
        if (clazz(target)==null) {
            return "null";
        }
        String name = clazz(target).getCanonicalName();
        return name==null ? "null" : name;
    }

    static Logger log(String name) {
        return Logger.getLogger(name);
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
