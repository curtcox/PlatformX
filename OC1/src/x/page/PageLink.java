package x.page;

import java.util.Arrays;

/**
 * A link to a screen.  This is analogous to an HTML link.
 */
public final class PageLink {

    public interface Factory {
        PageLink create();
    }

    public final Page page;
    public final PageTags tags;
    public final Object[] args;

    private PageLink(Page page, String screen, Object... args) {
        this.page = page;
        this.tags = PageTags.of(screen);
        this.args = args;
    }

    public static PageLink of(Page page, String name, Object... args) {
        return new PageLink(page,name);
    }

    public static PageLink of(String name, Object... args) {
        return new PageLink(null,name,args);
    }

    public String title() {
        return tags.toString();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        PageLink that = (PageLink) o;
        return tags.equals(that.tags);
    }

    @Override
    public String toString() {
        return tags + " " + Arrays.asList(args);
    }
}
