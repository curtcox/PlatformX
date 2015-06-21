package common.screen.dynamic;

import common.screen.Page;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

/**
 * A screen that is created by a dynamic layout method.
 */
final class DynamicScreen
    extends Page
{
    final ScreenContext.Provider controller;
    final ScreenLayoutProvider layoutProvider;
    final ScreenLink link;
    
    public DynamicScreen(ScreenLink link, ScreenContext.Provider controller, ScreenLayoutProvider layoutProvider) {
        super(link);
        this.link = link;
        this.controller = controller;
        this.layoutProvider = layoutProvider;
    }

    @Override
    public UIComponent layoutForPortrait() {
        return getLayout();
    }

    @Override
    public UIComponent layoutForLandscape() {
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
