package oc1.app;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.log.IssueReporter;
import oc1.log.Log;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
final class ExceptionLogger
    implements ActionListener
{

    void install() {
        getDisplay().addEdtErrorHandler(this);
    }
    
    Display getDisplay() {
        return Registry.get(Display.class);
    }

    Log getLog() {
        return Registry.get(LogManager.class).getLog(ExceptionLogger.class);
    }

    static ExceptionLogger of() {
        return new ExceptionLogger();
    }

    public void actionPerformed(ActionEvent event) {
        event.consume();
        log(event);
        IssueReporter.sendEmail();
    }

    void log(ActionEvent event) {
        Display display = getDisplay();
        if (display==null) {
            log("Display==null");
        } else {
            log(display);
        }
        log("Error " + event.getSource());
        log((Throwable)event.getSource());
    }

    void log(Display display) {
        log("Exception in AppName version " + display.getProperty("AppVersion", "Unknown"));
        log("OS "                           + display.getPlatformName());
        Form form = display.getCurrent();
        if (form==null) {
            log("Form==null");
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
