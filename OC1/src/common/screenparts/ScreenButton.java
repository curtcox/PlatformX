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
 */
public final class ScreenButton
    extends UIButton
{
    final Screen screen;
    final ScreenLink link;

    private ScreenButton(Builder builder) {
        super(builder.text);
        this.screen = builder.screen;
        this.link = builder.link;
    }

    @Override
    public void onTap() {
        screen().show();
    }

    private Screen screen() {
        if (screen!=null) {
            return screen;
        }
        return ScreenFactory.DEFAULT.create(link)[0];
    }

    public static class Builder {
        private String text;
        private Screen screen;
        private ScreenLink link;

        public UIButton build() {
            if (screen!=null || link !=null) {
                return new ScreenButton(this);
            }
            throw new IllegalStateException("No screen specified");
        }

        public Builder text(StringSource s) { return this; }
        public Builder text(String text) {
            this.text = text;
            return this;
        }
        public Builder image(String s) { return this; }
        public Builder leadingTo(Screen screen) {
            this.screen = screen;
            return this;
        }
        public Builder leadingTo(String s) { return this; }
        public Builder leadingTo(ScreenLink link) {
            this.link = link;
            return this;
        }
        public Builder action(Runnable r) { return this; }
    }

    public static Builder builder() {
        return new Builder();
    }


}
