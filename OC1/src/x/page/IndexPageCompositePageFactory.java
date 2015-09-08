package x.page;

import x.Registry;
import x.event.XLiveList;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

import java.util.Arrays;

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
        return pages.length > 1 ? indexPage(link,pages) : pages;
    }

    private Page[] indexPage(PageLink link, Page[] pages) {
        return indexPageFactory(pages).create(link);
    }

    private PageFactory indexPageFactory(Page[] pages) {
        return itemListScreenFactoryFactory().newFactory(liveList(pages), ItemToPageLink.PAGE);
    }

    private XLiveList liveList(Page[] pages) {
        return new XLiveList(Arrays.asList(pages));
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }
}
