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

    private ScreenButton(Builder builder) {
        super("");
        this.screen = builder.screen;
    }

    @Override
    public void onTap() {
        screen.show();
    }

    public static class Builder {
        private Screen screen;

        public UIButton build() {
            if (screen!=null) {
                return new ScreenButton(this);
            }
            throw new IllegalStateException("No screen specified");
        }

        public Builder text(StringSource s) { return this; }
        public Builder text(String s) { return this; }
        public Builder image(String s) { return this; }
        public Builder leadingTo(Screen screen) {
            this.screen = screen;
            return this;
        }
        public Builder leadingTo(String s) { return this; }
        public Builder leadingTo(ScreenLink link) { return this; }
        public Builder action(Runnable r) { return this; }
    }

    public static Builder builder() {
        return new Builder();
    }


}
