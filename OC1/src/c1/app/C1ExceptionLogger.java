package c1.app;

import c1.log.C1IssueReporter;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

final class C1ExceptionLogger
    implements ActionListener
{

    void install() {
        getDisplay().addEdtErrorHandler(this);
    }
    
    Display getDisplay() {
        return Registry.get(Display.class);
    }

    ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1ExceptionLogger.class,this);
    }

    static C1ExceptionLogger of() {
        return new C1ExceptionLogger();
    }

    public void actionPerformed(ActionEvent event) {
        event.consume();
        log(event);
        logDisplay();
        C1IssueReporter.sendEmail();
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
