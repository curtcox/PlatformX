package common.screen.dynamic;

import common.*;
import common.log.ILog;
import common.log.ILogManager;
import common.screen.ScreenLayout;
import common.ui.UIColumnLayout;
import common.ui.UIComponent;
import common.ui.UIGridLayout;
import common.ui.UILabel;
import hash.*;
import common.event.StringSource;

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

    public UIComponent getLayout(ScreenContext context) {
        String sourceCode = source.getString();
        if (!isValidHash(sourceCode)) {
            return messageScreen("Source is not valid Hash");
        }
        return screenForResult(getLayoutFromHash(sourceCode,context));
    }

    private boolean isValidHash(String sourceCode) {
        return sourceCode!=null && sourceCode.length()>0;
    }

    private UIComponent screenForResult(Object result) {
        if (result==null)                   { return messageScreen("(null)");            }
        if (result instanceof ScreenLayout) { return (UIComponent) result;              }
        if (result instanceof String)       { return messageScreen((String)result);      }
        if (result instanceof UIComponent)  { return componentScreen((UIComponent)result); }
        if (result instanceof SyntaxError)  { return errorScreen((SyntaxError) result);  }
        throw new IllegalArgumentException("result="+result);
    }
    
    private UIComponent componentScreen(UIComponent component) {
        return new UIGridLayout(1,1,component);
    }

    private UIComponent messageScreen(String message) {
        return componentScreen(label(message));
    }

    private UIComponent errorScreen(SyntaxError error) {
        return new UIColumnLayout(
                label(error.type.toString()),
                label(error.errorSource),
                label(error.methodSource)
        );
    }

    private UIComponent exception(Exception e) {
        return new UIGridLayout(2,1,
                label(e.getClass().toString()),
                label(e.getMessage()));
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

    private UILabel label(String text) {
        return new UILabel(text);
    }
    
    private NamedValues asNamedValues(ScreenContext screenContext) {
        return new ScreenContextAsNamedValues(screenContext);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(DynamicScreenLayoutProvider.class);
    }

}
