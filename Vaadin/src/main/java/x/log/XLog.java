package x.log;

import x.Registry;
import x.ui.IDisplay;
import x.ui.IForm;

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

    public void log(String message) {
        //log.info(message);
        getLogWriter().log(now() + ":" + thread() + ":" + screen() + prefix + message);
    }
    
    String now() {
        return hex(System.currentTimeMillis());
    }

    String thread() {
        return Thread.currentThread().getName();
    }

    private String hex(long value) {
        return "" + value;
    }
    
    private XLogWriter getLogWriter() {
        return XLogWriter.of();
    }

    private String screen() {
        IForm form = form();
        return (form==null) ? "null" : form.getTitle();
    }
    
    private IForm form() {
        return display().getCurrent();
    }

    private IDisplay display() {
        return Registry.get(IDisplay.class);
    }

}
