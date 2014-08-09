package oc1.screen;

import oc1.app.CurrentState;
import oc1.app.Registry;
import oc1.event.Change.Source;
import oc1.ui.ActionButton;
import oc1.event.StringSource;

/**
 * A button that will go to the given screen when tapped.
 * @author Curt
 */
public final class ScreenButton {

    public static ActionButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,String screen, Screen previous) {
        ActionButton button = textActionAndLeadingTo(text,runnable,screenFactory().create(new ScreenLink(screen,previous)));
        button.setIcon(image);
        return button;
    }

    public static ActionButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,Screen screen) {
        ActionButton button = textActionAndLeadingTo(text,runnable,screen);
        button.setIcon(image);
        return button;
    }

    public static ActionButton textAndImageLeadingTo(String text,String image,final String screen,final Screen previous) {
        ActionButton button = textAndLeadingTo(text,screen,previous);
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

    public static ActionButton textAndLeadingTo(String text,final String screen,final Screen previous) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(new ScreenLink(screen,previous)).show();
            }
        };
    }

    private static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }
    
    public static ActionButton textActionAndLeadingTo(String text,final Runnable runnable, final Screen screen) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                runnable.run();
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

    public static ActionButton lazyWithTextAndLeadingTo(String text,final String screen, final Screen previous, final Object...args) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(new ScreenLink(screen,previous,args)).show();
            }
        };
    }

    public static ActionButton lazyWithTextAndLeadingTo(StringSource source,String screen, final Screen previous) {
        final ActionButton button = lazyWithTextAndLeadingTo(source.getString(),screen,previous);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

}
