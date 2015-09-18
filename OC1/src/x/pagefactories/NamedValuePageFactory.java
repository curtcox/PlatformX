package x.pagefactories;

import x.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.uiwidget.XSearchableList;

import java.util.Arrays;

public final class NamedValuePageFactory
    implements PageFactory
{
    static final PageTags TAG = PageTags.of("NamedValue");

    public static PageFactory of() {
        return new NamedValuePageFactory();
    }

    @Override
    public Page[] create(PageLink link) {
        return (link.tags.matches(TAG))
            ? ItemsPage.of(tags(link),link,newSearchableList(link),itemToPageLink(link))
            : new Page[0];
    }

    public static PageLink linkTo(NamedValue value) {
        return PageLink.of(TAG.plus(value.name),value.value);
    }

    private ItemToPageLink itemToPageLink(final PageLink link) {
        return new ItemToPageLink() {
            @Override
            public PageLink pageLink(Object item) {
                throw new UnsupportedOperationException(link + " item=" + item);
            }
        };
    }

    private XSearchableList newSearchableList(PageLink link) {
        return listBuilder().items(items(link)).build();
    }

    private LiveList items(PageLink link) {
        return XLiveList.of(Arrays.asList(link.args));
    }

    private PageTags tags(PageLink link) {
        return link.tags;
    }

    XSearchableList.Builder listBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }

}
