package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import hash.Context;
import hash.Hash;
import oc1.event.StringSource;
import oc1.log.Log;
import oc1.log.LogManager;
import oc1.net.RawNetwork;

/**
 *
 * @author Curt
 */
public class DynamicScreenLayout
    implements ScreenLayout.Provider
{
    final StringSource source;
    
    DynamicScreenLayout(StringSource source) {
        this.source = source;    
    }

    public ScreenLayout getLayout(ScreenContext context) {
        Object result = getLayoutFromHash(context);
        if (result instanceof ScreenLayout) {
            return (ScreenLayout) result;
        }
        if (result==null) {
            result = "(null)";            
        }
        if (result instanceof String) {
            return new ScreenLayout(new GridLayout(1,1),new Label((String)result));
        }
        throw new IllegalArgumentException("result="+result);
    }

    private Object getLayoutFromHash(ScreenContext context) {
        try {
            return Hash.invoke(source.getString(),"layout",context(context));
        } catch (Exception e) {
            log(e);
            return e.toString();
        }
    }

    private Context context(ScreenContext context) {
        return new Context("#",new ScreenContextAsNamedInvokableProvider(context));
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private Log getLog() {
        return LogManager.of().getLog(RawNetwork.class);    
    }

}
