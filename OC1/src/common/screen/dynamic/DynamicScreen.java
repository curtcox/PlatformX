package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

/**
 * A screen that is created by a dynamic layout method.
 */
final class DynamicScreen
    extends Screen
{
    final ScreenContext.Provider controller;
    final ScreenLayoutProvider layoutProvider;
    
    public DynamicScreen(ScreenLink link, ScreenContext.Provider controller, ScreenLayoutProvider layoutProvider) {
        super(link);
        this.controller = controller;
        this.layoutProvider = layoutProvider;
    }

    @Override
    protected UIComponent layoutForPortrait() {
        return getLayout();
    }

    @Override
    protected UIComponent layoutForLandscape() {
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

    UIComponent getLayout() {
        return layoutProvider.getLayout(link,getContext());
    }
}
