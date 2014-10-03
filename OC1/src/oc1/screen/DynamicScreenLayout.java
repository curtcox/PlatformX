package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import hash.Context;
import hash.Hash;
import oc1.event.StringSource;

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
        if (result instanceof String) {
            return new ScreenLayout(new GridLayout(1,1),new Label((String)result));
        }
        throw new IllegalArgumentException();
    }

    private Object getLayoutFromHash(ScreenContext context) {
        return Hash.invoke(source.getString(),"layout",context(context));
    }

    private Context context(ScreenContext context) {
        return new Context("#",new ScreenContextAsNamedInvokableProvider(context));
    }
}
