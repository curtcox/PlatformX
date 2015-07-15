package x.pages;

import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.dynamic.GlobPageFactory;
import x.uiwidget.XComponent;

public final class CustomComponentPage
    extends Page
{
    
    public static final PageFactory FACTORY = GlobPageFactory.filter("Custom", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{new CustomComponentPage(link)};
        }
    });

    CustomComponentPage(PageLink link) {
        super(link);
    }
    
    @Override
    public XComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
