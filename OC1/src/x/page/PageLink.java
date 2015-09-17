package x.page;

import java.util.Arrays;

/**
 * A link to a screen.  This is analogous to an HTML link.
 */
public final class PageLink {

    public interface Factory {
        PageLink create();
    }

    /**
     * The (possibly null) page this link references.
     * If the page is null, then the tags and args will be used to find
     * the matching page or pages.
     */
    public final Page page;
    public final PageTags tags;
    public final Object[] args;

    private PageLink(Page page, String tags, Object... args) {
        this.page = page;
        this.tags = PageTags.of(tags);
        this.args = args;
    }

    public static PageLink of(Page page, String tags, Object... args) {
        return new PageLink(page,tags,args);
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
