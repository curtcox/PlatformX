package x.page;

import java.util.Arrays;

/**
 * A link to a screen page.  This is analogous to an HTML link.
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
    public final Page page; // possibly null
    public final PageTags tags;
    public final Object[] args;

    // Use one of the factory methods
    private PageLink(Page page, String tags, Object... args) {
        this.page = page;
        this.tags = PageTags.of(tags);
        this.args = args;
    }

    /**
     * For creating a link when you already know exactly the page that you want to
     * go to AND want to go there and only there.
     */
    public static PageLink of(Page page, String tags, Object... args) {
        return new PageLink(page,tags,args);
    }

    /**
     * For creating a link to a page or pages to be determined later.
     * Use this when you want the possibility of PageFactories determining the
     * exact page.
     */
    public static PageLink of(String tags, Object... args) {
        return new PageLink(null,tags,args);
    }

    /**
     * For creating a link to a page or pages to be determined later.
     * Use this when you want the possibility of PageFactories determining the
     * exact page.
     */
    public static PageLink of(PageTags tags, Object... args) {
        return new PageLink(null,tags.toString(),args);
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
