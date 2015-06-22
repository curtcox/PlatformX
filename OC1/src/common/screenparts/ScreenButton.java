package common.screenparts;

import common.event.StringSource;
import common.screen.Page;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.uiwidget.UIButton;

/**
 * A button that will go to the given page when tapped.
 */
public final class ScreenButton
    extends UIButton
{
    final Page page;
    public final ScreenLink link;

    private ScreenButton(Builder builder) {
        super(builder.text);
        this.page = builder.page;
        this.link = builder.link;
        this.icon = builder.image;
    }

    @Override
    public void onTap() {
        screen().show();
    }

    private Screen screen() {
        if (page!=null) {
            return new Screen(link,page);
        }
        Screen.show(link, ScreenFactory.DEFAULT);
        return Screen.getShowing();
    }

    public static class Builder {
        private String text;
        private String image;
        private Page page;
        private ScreenLink link;

        public UIButton build() {
            if (page !=null || link !=null) {
                return new ScreenButton(this);
            }
            throw new IllegalStateException("No page specified");
        }

        public Builder text(StringSource s) { return this; }
        public Builder text(String text) {
            this.text = text;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Builder leadingTo(Page screen) {
            this.page = screen;
            return this;
        }
        public Builder leadingTo(String target) {
            link = ScreenLink.of(target);
            return this;
        }
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
