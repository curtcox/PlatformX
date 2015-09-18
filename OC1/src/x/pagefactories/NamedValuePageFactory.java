package x.pagefactories;

import x.Registry;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.uiwidget.XSearchableList;

public final class NamedValuePageFactory
    implements PageFactory
{

    @Override
    public Page[] create(PageLink link) {
        return ItemsPage.of(tags(link),link,newSearchableList(link),itemToPageLink(link));
    }

    private ItemToPageLink itemToPageLink(PageLink link) {
        return new ItemToPageLink() {
            @Override
            public PageLink pageLink(Object item) {
                throw new UnsupportedOperationException("item=" + item);
            }
        };
    }

    private XSearchableList newSearchableList(PageLink link) {

        return null;
    }

    private PageTags tags(PageLink link) {
        return link.tags;
    }

    XSearchableList.Builder listBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }
}
