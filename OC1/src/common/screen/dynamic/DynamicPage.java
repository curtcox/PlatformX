package common.screen.dynamic;

import common.page.Page;
import common.page.PageLink;
import common.uiwidget.UIComponent;

/**
 * A page that is created by a dynamic layout method.
 */
final class DynamicPage
    extends Page
{
    final ScreenContext.Provider controller;
    final ScreenLayoutProvider layoutProvider;
    final PageLink link;
    
    public DynamicPage(PageLink link, ScreenContext.Provider controller, ScreenLayoutProvider layoutProvider) {
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
