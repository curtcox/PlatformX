package c1.screens;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;
import common.uiwidget.UIComponent;

public final class CustomComponentScreen
    extends Screen
{
    
    public static final ScreenFactory FACTORY = new GlobScreenFactory("Custom") {
        public Screen doCreate(ScreenLink link) {
            return new CustomComponentScreen();
        }     
    };

    CustomComponentScreen() {
        super(ScreenLink.of("Custom"));
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
