package common.screenparts;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.app.CurrentState;
import common.Registry;
import common.event.Change.Source;
import common.event.StringSource;
import common.uiwidget.UIButton;

/**
 * A button that will go to the given screen when tapped.
 * @author Curt
 */
public final class ScreenButton {

    public static UIButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,String screen) {
        UIButton button = textActionAndLeadingTo(text,runnable,screenFactory().create(ScreenLink.of(screen)));
        button.icon=image;
        return button;
    }

    public static UIButton textImageActionAndLeadingTo(String text,String image,Runnable runnable,Screen screen) {
        UIButton button = textActionAndLeadingTo(text,runnable,screen);
        button.icon=image;
        return button;
    }

    public static UIButton textAndImageLeadingTo(String text,String image,final String screen) {
        UIButton button = textAndLeadingTo(text,screen);
        button.icon=image;
        return button;
    }

    public static UIButton textAndLeadingTo(String text,final Screen screen) {
        return new UIButton(text) {
            @Override
            public void onTap() {
                screen.show();
            }
        };
    }

    public static UIButton textAndLeadingTo(String text,final ScreenLink link) {
        return new UIButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(link).show();
            }
        };
    }

    public static UIButton textAndLeadingTo(String text,final String screen) {
        return new UIButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(ScreenLink.of(screen)).show();
            }
        };
    }

    private static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }
    
    public static UIButton textActionAndLeadingTo(String text,final Runnable runnable, final Screen screen) {
        return new UIButton(text) {
            @Override
            public void onTap() {
                runnable.run();
                screen.show();
            }
        };
    }

    public static UIButton textWatchingAndLeadingTo(StringSource source,Source change,Screen screen) {
        final UIButton button = textAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(change, source);
        return button;
    }

    public static UIButton textAndLeadingTo(StringSource source,Screen screen) {
        final UIButton button = textAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

    public static UIButton textAndLeadingTo(StringSource source,String screenName) {
        final UIButton button = textAndLeadingTo(source.getString(),screenName);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

    public static UIButton lazyWithTextAndLeadingTo(String text,final String screen, final Object...args) {
        return new UIButton(text) {
            @Override
            public void onTap() {
                screenFactory().create(ScreenLink.of(screen,args)).show();
            }
        };
    }

    public static UIButton lazyWithTextAndLeadingTo(StringSource source,String screen) {
        final UIButton button = lazyWithTextAndLeadingTo(source.getString(),screen);
        button.updateTextOnChange(CurrentState.get(), source);
        return button;
    }

}
