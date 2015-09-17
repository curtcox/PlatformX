package x.pagefactories;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

public final class NamedValueToPageLink
    implements ItemToPageLink
{
    @Override
    public PageLink pageLink(Object item) {
        NamedValue pair = (NamedValue) item;
        return Page.withFixedLayout(title(pair), layout(pair)).link;
    }

    private String title(NamedValue pair) {
        return pair.name;
    }

    private XComponent layout(NamedValue pair) {
        return new XLabel("=" +pair.value);
    }
}
