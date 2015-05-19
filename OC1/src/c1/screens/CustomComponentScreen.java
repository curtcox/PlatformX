package c1.screens;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import common.uiwidget.UIComponent;

public final class CustomComponentScreen
    extends Screen
{
    
    public static final ScreenFactory FACTORY = new GlobScreenFactory("Custom", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[] {new CustomComponentScreen(link)};
        }
    });

    CustomComponentScreen(ScreenLink link) {
        super(link);
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
