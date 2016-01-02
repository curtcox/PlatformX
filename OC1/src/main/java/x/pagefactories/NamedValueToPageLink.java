package x.pagefactories;

import x.page.PageLink;
import x.util.NamedValue;

public final class NamedValueToPageLink
    implements ItemToPageLink
{
    @Override
    public PageLink pageLink(Object item) {
        NamedValue value = (NamedValue) item;
        return NamedValuePageFactory.linkTo(value);
    }

}
