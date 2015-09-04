package x.pageparts;

import x.event.StringSource;
import x.page.Page;
import x.page.PageLink;
import x.screen.Screen;
import x.uiwidget.XButton;

/**
 * A button that will go to the given page when tapped.
 */
public final class XScreenButton
    extends XButton
{
    final Page page;
    public final PageLink link;

    private XScreenButton(Builder builder) {
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
            return Screen.of(page);
        }
        Screen.show(link);
        return Screen.getShowing();
    }

    public static class Builder {
        private String text;
        private String image;
        private Page page;
        private PageLink link;

        public XButton build() {
            if (page !=null || link !=null) {
                return new XScreenButton(this);
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
