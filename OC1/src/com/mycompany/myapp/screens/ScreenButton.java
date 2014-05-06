package com.mycompany.myapp.screens;

import com.mycompany.myapp.ui.ActionButton;

/**
 * A button that will go to the given screen when tapped.
 * @author Curt
 */
public final class ScreenButton {
    
    public static ActionButton of(String text,final Screen screen) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screen.show();
            }
        };
    }
}
