package oc1.app;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import common.ILog;
import common.ILogManager;
import common.Registry;
import oc1.log.IssueReporter;

final class ExceptionLogger
    implements ActionListener
{

    void install() {
        getDisplay().addEdtErrorHandler(this);
    }
    
    Display getDisplay() {
        return Registry.get(Display.class);
    }

    ILog getLog() {
        return Registry.get(ILogManager.class).getLog(ExceptionLogger.class);
    }

    static ExceptionLogger of() {
        return new ExceptionLogger();
    }

    public void actionPerformed(ActionEvent event) {
        event.consume();
        log(event);
        logDisplay();
        IssueReporter.sendEmail();
    }

    void logDisplay() {
        Display display = getDisplay();
        if (display==null) {
            log("Display==null");
        } else {
            log(display);
        }
    }
    
    void log(ActionEvent event) {
        log("Error " + event.getSource());
        log((Throwable)event.getSource());
    }

    void log(Display display) {
        log("Exception in AppName version " + display.getProperty("AppVersion", "Unknown"));
        log("OS "                           + display.getPlatformName());
        Form form = display.getCurrent();
        if (form==null) {
            log("Form=null");
         } else {
            log("Current Form "             + form.getName());
        }
    }
    
    void log(String message) {
        getLog().log(message);
    }

    void log(Throwable t) {
        getLog().log(t);
    }

}
