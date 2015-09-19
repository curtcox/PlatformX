package x.pagefactories;

import x.app.Registry;
import x.event.LiveList;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.uiwidget.XSearchableList;
import x.util.NamedValue;
import x.event.NamedValueListSource;

import java.util.Arrays;

public final class NamedValuePageFactory
    implements PageFactory
{
    static final PageTags TAG = PageTags.of("NamedValue");

    public static final ItemToPageLink ITEM_TO_PAGELINK =
        new ItemToPageLink() {
            @Override
            public PageLink pageLink(Object item) {
                return linkTo(namedValue(item));
            }
        };

    public static PageFactory of() {
        return new NamedValuePageFactory();
    }

    @Override
    public Page[] create(PageLink link) {
        return (link.tags.matches(TAG))
            ? ItemsPage.of(tags(link), link, newSearchableList(link), ITEM_TO_PAGELINK)
            : new Page[0];
    }

    public static PageLink linkTo(NamedValue value) {
        return PageLink.of(TAG.plus(value.name), value.value);
    }

    private static NamedValue namedValue(Object item) {
        return item instanceof NamedValue
            ? (NamedValue) item
            : new NamedValue("value",item);
    }

    private XSearchableList newSearchableList(PageLink link) {
        return listBuilder().items(items(link)).build();
    }

    private LiveList items(PageLink link) {
        Object[] args = link.args;
        if (args.length>1) {
            return XLiveList.of(Arrays.asList(args));
        }
        if (args.length==1) {
            Object arg = args[0];
            if (arg instanceof NamedValueListSource) {
                NamedValueListSource source = (NamedValueListSource) arg;
                return source.asNamedValues();
            } else {
                return XLiveList.of(Arrays.asList(arg));
            }
        }
        throw new IllegalArgumentException();
    }

    private PageTags tags(PageLink link) {
        return link.tags;
    }

    XSearchableList.Builder listBuilder() {
        return Registry.get(XSearchableList.Factory.class).builder();
    }

}
