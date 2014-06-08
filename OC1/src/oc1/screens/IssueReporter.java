package oc1.screens;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import oc1.Registry;
import oc1.log.LogWriter;

/**
 *
 * @author Curt
 */
final class IssueReporter {

    static void sendEmail() {
        String[] recipients = new String[]{"curtcox@gmail.com"};
        String subject = "Oyster Cracker Issue Report";
        getDisplay().sendMessage(recipients, subject, createMessage());
    }
    
    static Display getDisplay() {
        return Registry.get(Display.class);
    }

    static Message createMessage() {
        return new Message(createContent());
    }

    private static String createContent() {
        StringBuilder out = new StringBuilder();
        out.append(HomeScreen.VERSION + "/r/n");
        out.append(getLogWriter().dump());
        return out.toString();
    }

    private static LogWriter getLogWriter() {
        return LogWriter.of();
    }

}
