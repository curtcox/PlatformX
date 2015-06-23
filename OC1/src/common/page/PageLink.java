package common.page;

import java.util.Arrays;

/**
 * A link to a screen.  This is analogous to an HTML link.
 */
public final class PageLink {

    public interface Factory {
        PageLink create();
    }

    public final ScreenTags tags;
    public final Object[] args;

    private PageLink(String screen, Object... args) {
        this.tags = ScreenTags.of(screen);
        this.args = args;
    }

    public static PageLink of(String name, Object... args) {
        return new PageLink(name,args);
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
