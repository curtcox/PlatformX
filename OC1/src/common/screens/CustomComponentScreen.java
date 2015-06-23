package common.screens;

import common.page.Page;
import common.screen.PageFactory;
import common.page.ScreenLink;
import common.screen.dynamic.GlobPageFactory;
import common.uiwidget.UIComponent;

public final class CustomComponentScreen
    extends Page
{
    
    public static final PageFactory FACTORY = GlobPageFactory.filter("Custom", new PageFactory() {
        @Override
        public Page[] create(ScreenLink link) {
            return new Page[]{new CustomComponentScreen(link)};
        }
    });

    CustomComponentScreen(ScreenLink link) {
        super(link);
    }
    
    @Override
    public UIComponent layoutForPortrait() {
        return new CustomComponent();
    }

}
