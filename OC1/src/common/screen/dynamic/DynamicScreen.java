package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenLayout;

/**
 * A screen that is created by a dynamic layout method.
 */
final class DynamicScreen
    extends Screen
{
    final ScreenContext.Provider controller;
    final ScreenLayout.Provider layoutProvider;
    
    public DynamicScreen(String name, ScreenContext.Provider controller, ScreenLayout.Provider layoutProvider) {
        super(name);
        this.controller = controller;
        this.layoutProvider = layoutProvider;
    }

    @Override
    protected ScreenLayout layoutForPortrait() {
        return getLayout();
    }

    @Override
    protected ScreenLayout layoutForLandscape() {
        return getLayout();
    }
    
    private ScreenContext getContext() {
        ScreenContext context = controller.getContext();
        context.put("portrait",portraitGetter());
        context.put("landscape", landscapeGetter());
        context.putAll(new DynamicScreenLayoutMethods());
        context.putAll(new DynamicScreenComponentMethods());
        context.putAll(new DynamicScreenMethodInvocations());
        return context;
    }

    private Getter portraitGetter() {
        return new Getter() {
            public Object get() {
                return isPortrait();
            }
        };    
    }

    private Getter landscapeGetter() {
        return new Getter() {
            public Object get() {
                return !isPortrait();
            }
        };    
    }
    
    ScreenLayout getLayout() {
        return layoutProvider.getLayout(getContext());
    }
}
