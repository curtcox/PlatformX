package common.screens;

import common.screen.Page;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import common.uiwidget.UIComponent;

public final class CustomComponentScreen
    extends Page
{
    
    public static final ScreenFactory FACTORY = GlobScreenFactory.filter("Custom", new ScreenFactory() {
        @Override
        public Page[] create(ScreenLink link) {
            return new Page[]{new CustomComponentScreen(link)};
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
