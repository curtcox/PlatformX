package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import hash.NamedValues;
import hash.Run;
import oc1.event.StringSource;
import oc1.log.*;

public final class DynamicScreenLayout
    implements ScreenLayout.Provider
{
    final StringSource source;
    
    DynamicScreenLayout(StringSource source) {
        this.source = source;    
    }

    public ScreenLayout getLayout(ScreenContext context) {
        String sourceCode = source.getString();
        if (!isValidHash(sourceCode)) {
            return messageScreen("Source is not valid Hash");
        }
        return screenForResult(getLayoutFromHash(sourceCode,context));
    }

    private boolean isValidHash(String sourceCode) {
        return sourceCode!=null && sourceCode.length()>0;
    }

    private ScreenLayout screenForResult(Object result) {
        if (result==null) {
            return messageScreen("(null)");
        }
        if (result instanceof ScreenLayout) {
            return (ScreenLayout) result;
        }
        if (result instanceof String) {
            return messageScreen((String)result);
        }
        throw new IllegalArgumentException("result="+result);
    }
    
    private ScreenLayout messageScreen(String message) {
        return new ScreenLayout(new GridLayout(1,1),new Label(message));
    }

    private Object getLayoutFromHash(String sourceCode,ScreenContext context) {
        try {
            return Run
                .source(sourceCode)
                .method("layout")
                .namedValues(context(context))
                .args();
        } catch (Exception e) {
            log(e);
            return e.toString();
        }
    }

    private NamedValues context(ScreenContext context) {
        return new ScreenContextAsNamedValues(context);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private Log getLog() {
        return LogManager.of().getLog(DynamicScreenLayout.class);    
    }

}
