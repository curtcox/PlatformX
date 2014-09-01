package oc1.screen;

import oc1.event.StringSource;

/**
 *
 * @author Curt
 */
public class DynamicScreenLayout
    implements ScreenLayout.Provider
{

    DynamicScreenLayout(StringSource source) {
    }

    public ScreenLayout getLayout(ScreenContext context) {
        throw new RuntimeException();
    }

    
}
