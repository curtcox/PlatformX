package x.log;

import java.util.logging.Logger;

public final class XLog
    implements ILog
{

    final Class clazz;
    final String name;
    final String prefix;
    final Logger log;
    
    XLog(Class clazz) {
        this.clazz = clazz;
        name = clazz.getCanonicalName();
        prefix = ":" + name + ":";
        log = Logger.getLogger(name);
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
        getLogWriter().log(clazz,message,details);
    }

    private XLogWriter getLogWriter() {
        return XLogWriter.of();
    }

}
