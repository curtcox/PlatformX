package common.screens;

import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.page.dynamic.GlobPageFactory;
import common.uiwidget.UIComponent;

public final class CustomComponentScreen
    extends Page
{
    
    public static final PageFactory FACTORY = GlobPageFactory.filter("Custom", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{new CustomComponentScreen(link)};
        }
    });

    CustomComponentScreen(PageLink link) {
        super(link);
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
