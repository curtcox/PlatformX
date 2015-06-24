package common.pages;

import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.page.dynamic.GlobPageFactory;
import common.uiwidget.UIComponent;

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
    public UIComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
