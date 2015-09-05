package x.page;

/**
 * A PageFactory that returns either:
 * - a single page from the underlying page factory
 * - an index of the pages from the underlying factory
 */
public final class IndexPageCompositePageFactory
    implements PageFactory
{
    private final PageFactory inner;

    public IndexPageCompositePageFactory(PageFactory inner) {
        this.inner = inner;
    }

    @Override
    public Page[] create(PageLink link) {
        Page[] pages =  inner.create(link);
        return pages.length > 1 ? new Page[1] : pages;
    }
}
