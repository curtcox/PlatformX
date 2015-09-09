package x.pagefactories;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

public final class KeyValuePairToPageLink
    implements ItemToPageLink
{
    @Override
    public PageLink pageLink(Object item) {
        KeyValuePair pair = (KeyValuePair) item;
        return Page.withFixedLayout(title(pair), layout(pair)).link;
    }

    private String title(KeyValuePair pair) {
        return pair.key;
    }

    private XComponent layout(KeyValuePair pair) {
        return new XLabel("=" +pair.value);
    }
}
