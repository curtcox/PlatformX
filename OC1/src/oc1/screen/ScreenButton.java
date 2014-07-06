package oc1.screen;

import oc1.app.CurrentState;
import oc1.event.Change.Source;
import oc1.ui.ActionButton;
import oc1.ui.StringSource;

/**
 * A button that will go to the given screen when tapped.
 * @author Curt
 */
public final class ScreenButton {

    public static ActionButton textAndImageLeadingTo(String text,String image,final Screen screen) {
        ActionButton button = textAndLeadingTo(text,screen);
        button.setIcon(image);
        return button;
    }

    public static ActionButton textAndLeadingTo(String text,final Screen screen) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screen.show();
            }
        };
    }
   
    public static ActionButton textWatchingAndLeadingTo(StringSource source,Source change,Screen screen) {
        final ActionButton button = textAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(change, source);
        return button;
    }

    public static ActionButton textAndLeadingTo(StringSource source,Screen screen) {
        final ActionButton button = textAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

    public static ActionButton lazyWithTextAndLeadingTo(String text,final ScreenFactory screenFactory) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory.create().show();
            }
        };
    }

    public static ActionButton lazyWithTextAndLeadingTo(StringSource source,ScreenFactory screen) {
        final ActionButton button = lazyWithTextAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

}
