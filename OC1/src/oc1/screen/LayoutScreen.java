package oc1.screen;

import com.codename1.ui.Component;

/**
 * A screen that is created by a dynamic layout method.
 */
final class LayoutScreen
    extends Screen
{
    final ScreenController controller;
    final ScreenLayout.Provider layoutProvider;
    
    public LayoutScreen(String name, ScreenController controller, ScreenLayout.Provider layoutProvider) {
        super(name);
        this.controller = controller;
        this.layoutProvider = layoutProvider;
    }

    @Override
    protected void layoutForPortrait() {
        layout();
    }

    @Override
    protected void layoutForLandscape() {
        layout();
    }
    
    private ScreenContext getContext() {
        ScreenContext context = controller.getContext();
        context.put("portrait",portraitGetter());
        context.put("landscape",landscapeGetter());
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
    
    private void layout() {
        ScreenLayout layout = getLayout();
        form.setLayout(layout.layout);
        for(Component component : layout.components) {
            Components.removeFromParentIfAny(component);
            form.addComponent(component);
        }
    }
    
    ScreenLayout getLayout() {
        return layoutProvider.getLayout(getContext());
    }
}
