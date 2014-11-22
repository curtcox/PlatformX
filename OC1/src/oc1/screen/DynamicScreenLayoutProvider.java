package oc1.screen;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import hash.*;
import oc1.event.StringSource;
import oc1.log.*;

/**
 * Provides a ScreenLayout that is dynamically generated at runtime.
 * @author Curt
 */
public final class DynamicScreenLayoutProvider
    implements ScreenLayout.Provider
{
    final StringSource source;
    static final String layout = "layout";
    
    DynamicScreenLayoutProvider(StringSource source) {
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
        if (result==null)                   { return messageScreen("(null)");            }
        if (result instanceof ScreenLayout) { return (ScreenLayout) result;              }
        if (result instanceof String)       { return messageScreen((String)result);      }
        if (result instanceof Component)    { return componentScreen((Component)result); }
        if (result instanceof SyntaxError)  { return errorScreen((SyntaxError) result);  }
        throw new IllegalArgumentException("result="+result);
    }
    
    private ScreenLayout componentScreen(Component component) {
        return new ScreenLayout(new GridLayout(1,1),component);
    }

    private ScreenLayout messageScreen(String message) {
        return componentScreen(new Label(message));
    }

    private ScreenLayout errorScreen(SyntaxError error) {
        return new ScreenLayout(new BoxLayout(BoxLayout.Y_AXIS),
                label(error.type.toString()),
                label(error.errorSource),
                label(error.methodSource)
        );
    }

    private ScreenLayout exception(Exception e) {
        return new ScreenLayout(new GridLayout(2,1),
                label(e.getClass().toString()),
                label(e.getMessage())
        );
    }

    private Object getLayoutFromHash(String sourceCode,ScreenContext screenContext) {
        try {
            return Run
                .source(sourceCode)
                .method(layout)
                .namedValues(asNamedValues(screenContext))
                .args();
        } catch (Exception e) {
            log(e);
            return exception(e);
        }
    }

    private SpanLabel label(String text) {
        return new SpanLabel(text);
    }
    
    private NamedValues asNamedValues(ScreenContext screenContext) {
        return new ScreenContextAsNamedValues(screenContext);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private Log getLog() {
        return LogManager.of().getLog(DynamicScreenLayoutProvider.class);    
    }

}
