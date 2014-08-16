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

    public static ActionButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,String screen) {
        ActionButton button = textActionAndLeadingTo(text,runnable,screenFactory().create(new ScreenLink(screen)));
        button.setIcon(image);
        return button;
    }

    public static ActionButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,Screen screen) {
        ActionButton button = textActionAndLeadingTo(text,runnable,screen);
        button.setIcon(image);
        return button;
    }

    public static ActionButton textAndImageLeadingTo(String text,String image,final String screen) {
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

    public static ActionButton textAndLeadingTo(String text,final ScreenLink link) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(link).show();
            }
        };
    }

    public static ActionButton textAndLeadingTo(String text,final String screen) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(new ScreenLink(screen)).show();
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

    public static ActionButton textAndLeadingTo(StringSource source,String screenName) {
        final ActionButton button = textAndLeadingTo(source.getString(),screenName);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

    public static ActionButton lazyWithTextAndLeadingTo(String text,final String screen, final Object...args) {
        return new ActionButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(new ScreenLink(screen,args)).show();
            }
        };
    }

    public static ActionButton lazyWithTextAndLeadingTo(StringSource source,String screen) {
        final ActionButton button = lazyWithTextAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

}
