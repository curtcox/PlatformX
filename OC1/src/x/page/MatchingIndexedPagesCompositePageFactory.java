package x.page;

import x.Registry;
import x.event.XLiveList;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.ItemToPageLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A PageFactory that returns all of the matching pages from the factories it contains.
 */
final class MatchingIndexedPagesCompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;

    MatchingIndexedPagesCompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }

    @Override
    public Page[] create(PageLink link) {
        List<Page> all = new ArrayList();
        for (PageFactory factory : factories) {
            Page[] pages = factory.create(link);
            if (pages.length>0) {
                if (pages.length==1) {
                    all.add(pages[0]);
                } else {
                    all.addAll(Arrays.asList(indexPage(indexTags(factory,link), link, pages)));
                }
            }
        }
        Page[] pages = all.toArray(new Page[0]);
        return pages.length > 1 ? indexPage(indexTags(this,link),link,pages) : pages;
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
        return new XLiveList(java.util.Arrays.asList(pages));
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

}
