package oc1.util;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import oc1.log.LogManager;

public final class Reflection {

    public static void set(Object object, String field, Object value) {
        log("object " + object + " field " + field + " value " + value);
        Button button = (Button) object;
        button.setTextPosition(Label.BOTTOM);
    }

    private static void log(String message) {
        LogManager.of().getLog(Reflection.class).log(message);    
    }

}
