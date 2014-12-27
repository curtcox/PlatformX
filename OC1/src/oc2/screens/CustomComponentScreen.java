package oc2.screens;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLayout;
import common.screen.ScreenLink;
import common.screen.dynamic.GlobScreenFactory;

public final class CustomComponentScreen
    extends Screen
{
    
    public static final ScreenFactory FACTORY = new GlobScreenFactory("Custom") {
        public Screen doCreate(ScreenLink link) {
            return new CustomComponentScreen();
        }     
    };

    CustomComponentScreen() {
        super("Custom");
    }
    
    @Override
    public ScreenLayout layoutForPortrait() {
        return new ScreenLayout(new CustomComponent());
    }

}
