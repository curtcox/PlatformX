package oc1.log;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import common.Registry;
import oc1.app.Version;
import oc1.device.DeviceInfo;

public final class IssueReporter {

    public static void sendEmail() {
        String[] recipients = new String[]{"curtcox@gmail.com"};
        String subject = "Oyster Cracker Issue Report for version " + Version.VERSION;
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
        out.append(Version.VERSION + "\r\n");
        out.append(DeviceInfo.dump());
        out.append(getLogWriter().dump());
        return out.toString();
    }

    private static LogWriter getLogWriter() {
        return LogWriter.of();
    }

}
