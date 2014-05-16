package com.mycompany.myapp.screens;

import com.mycompany.myapp.CurrentState;
import com.mycompany.myapp.event.Change.Source;
import com.mycompany.myapp.ui.ActionButton;
import com.mycompany.myapp.ui.StringSource;

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

    public static ActionButton of(StringSource source,Source change,Screen screen) {
        final ActionButton button = of(source.getString(),screen);
        button.updateTextOnChange(change, source);
        return button;
    }

    public static ActionButton of(StringSource source,Screen screen) {
        final ActionButton button = of(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

}
