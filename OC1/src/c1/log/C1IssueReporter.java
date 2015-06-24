package c1.log;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import x.Registry;
import c1.app.Version;
import c1.device.C1DeviceInfo;
import x.log.CommonLogWriter;

public final class C1IssueReporter {

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
        out.append(C1DeviceInfo.dump());
        out.append(getLogWriter().dump());
        return out.toString();
    }

    private static CommonLogWriter getLogWriter() {
        return CommonLogWriter.of();
    }

}
