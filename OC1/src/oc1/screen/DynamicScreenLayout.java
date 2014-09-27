package oc1.screen;

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
        return (ScreenLayout) Hash.invoke(source.getString(),"layout",context(context));
    }

    private Context context(ScreenContext context) {
        return null;
    }
}
