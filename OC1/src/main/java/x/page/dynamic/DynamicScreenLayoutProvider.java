package x.page.dynamic;

import hash.NamedValues;
import hash.Run;
import hash.SyntaxError;
import x.app.Registry;
import x.event.StringSource;
import x.log.ILog;
import x.log.ILogManager;
import x.page.PageLink;
import x.uiwidget.XColumn;
import x.uiwidget.XComponent;
import x.uiwidget.XGrid;
import x.uiwidget.XLabel;

/**
 * Provides a ScreenLayout that is dynamically generated at runtime.
 */
public final class DynamicScreenLayoutProvider
    implements ScreenLayoutProvider
{
    final StringSource source;
    static final String layout = "layout";
    
    DynamicScreenLayoutProvider(StringSource source) {
        this.source = source;    
    }

    public XComponent getLayout(PageLink link, ScreenContext context) {
        String sourceCode = source.getString();
        if (!isValidHash(sourceCode)) {
            return messageScreen("Source is not valid Hash");
        }
        return screenForResult(getLayoutFromHash(sourceCode,context));
    }

    private boolean isValidHash(String sourceCode) {
        return sourceCode!=null && sourceCode.length()>0;
    }

    private XComponent screenForResult(Object result) {
        if (result==null)                   { return messageScreen("(null)");            }
        if (result instanceof XComponent)  { return (XComponent) result;               }
        if (result instanceof String)       { return messageScreen((String) result);      }
        if (result instanceof SyntaxError)  { return errorScreen((SyntaxError) result);  }
        throw new IllegalArgumentException("result="+result);
    }
    
    private XComponent messageScreen(String message) {
        return label(message);
    }

    private XComponent errorScreen(SyntaxError error) {
        return new XColumn(
                label(error.type.toString()),
                label(error.errorSource),
                label(error.methodSource)
        );
    }

    private XComponent exception(Exception e) {
        return new XGrid(2,1,
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

    private XLabel label(String text) {
        return new XLabel(text);
    }
    
    private NamedValues asNamedValues(ScreenContext screenContext) {
        return new ScreenContextAsNamedValues(screenContext);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}
