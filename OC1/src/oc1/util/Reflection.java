package oc1.util;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import common.ILog;
import common.ILogManager;
import common.Registry;
import oc1.log.LogManager;

public final class Reflection {

    public static void set(Object object, String field, Object value) {
        log("object " + object + " field " + field + " value " + value);
        Button button = (Button) object;
        button.setTextPosition(Label.BOTTOM);
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(Reflection.class);
    }

}
