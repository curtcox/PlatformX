package common.screenparts;

import common.event.StringSource;
import common.page.Page;
import common.page.PageFactory;
import common.screen.Screen;
import common.page.PageLink;
import common.uiwidget.UIButton;

/**
 * A button that will go to the given page when tapped.
 */
public final class ScreenButton
    extends UIButton
{
    final Page page;
    public final PageLink link;

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
        Screen.show(link, PageFactory.DEFAULT);
        return Screen.getShowing();
    }

    public static class Builder {
        private String text;
        private String image;
        private Page page;
        private PageLink link;

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
            this.link = screen.link;
            return this;
        }
        public Builder leadingTo(String target) {
            link = PageLink.of(target);
            return this;
        }
        public Builder leadingTo(PageLink link) {
            this.link = link;
            return this;
        }
        public Builder action(Runnable r) { return this; }
    }

    public static Builder builder() {
        return new Builder();
    }


}
