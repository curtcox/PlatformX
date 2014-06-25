package oc1.app;

import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.log.Log;
import oc1.log.LogManager;
import oc1.log.IssueReporter;

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
        log("Exception in AppName version " + getDisplay().getProperty("AppVersion", "Unknown"));
        log("OS " + getDisplay().getPlatformName());
        log("Error " + event.getSource());
        log("Current Form " + getDisplay().getCurrent().getName());
        log((Throwable)event.getSource());
    }

    void log(String message) {
        getLog().log(message);
    }

    void log(Throwable t) {
        getLog().log(t);
    }

}
