package x.pagefactories;

import x.app.Registry;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;

import java.util.ArrayList;
import java.util.List;

/**
 * A PageFactory that returns all of the matching pagesFromAllFactories from the factories it contains.
 */
public final class MatchingIndexedPagesCompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;

    public MatchingIndexedPagesCompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }

    @Override
    public Page[] create(PageLink link) {
        return ensureSinglePage(pagesFromAllFactories(link),link);
    }

    private Page[] ensureSinglePage(Page[] pages, PageLink link) {
        return pages.length > 1 ? indexPage(indexTags(this,link),link,pages) : pages;
    }

    private Page[] pagesFromAllFactories(PageLink link) {
        List<Page> all = new ArrayList();
        for (PageFactory factory : factories) {
            Page[] pages = factory.create(link);
            if (pages.length>0) {
                all.add(ensureSinglePage(pages,link)[0]);
            }
        }
        return all.toArray(new Page[0]);
    }

    private PageTags indexTags(PageFactory factory, PageLink link) {
        return PageTags.of(factory + " " + link);
    }

    private Page[] indexPage(PageTags tags, PageLink link, Page[] pages) {
        return indexPageFactory(tags,pages).create(link);
    }

    private PageFactory indexPageFactory(PageTags tags, Page[] pages) {
        return itemListScreenFactoryFactory().newFactory(tags, liveList(pages), ItemToPageLink.PAGE);
    }

    private XLiveList liveList(Page[] pages) {
        return XLiveList.of(java.util.Arrays.asList(pages));
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

}
