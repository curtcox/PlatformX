package oc1.log;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import oc1.screen.Screen;

/**
 *
 * @author Curt
 */
public final class Log {

    final Class clazz;
    final String prefix;
    
    Log(Class clazz) {
        this.clazz = clazz;
        prefix = ":" + clazz.getCanonicalName() + ":";
    }

    public void log(Throwable e) {
        log("Caught exception");
        log("class=" + e.getClass());
        log("message=" + e.getMessage());
        e.printStackTrace();
    }

    public void log(String message) {
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
    
    private LogWriter getLogWriter() {
        return LogWriter.of();
    }

    private String screen() {
        Form form = form();
        return (form==null) ? "null" : form.getTitle();
    }
    
    private Form form() {
        return Display.getInstance().getCurrent();
    }
}
