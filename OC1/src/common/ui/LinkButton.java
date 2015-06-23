package common.ui;

import common.Registry;
import common.screen.Screen;
import common.uiwidget.UIButton;
import common.screen.PageFactory;
import common.page.ScreenLink;

/**
 * A button that follows a link when you tap it.
 */
public final class LinkButton
    extends UIButton
{
    private final ScreenLink.Factory linkFactory;

    public LinkButton(final String name, final ScreenLink.Factory linkFactory) {
        super(name);
        this.linkFactory = linkFactory;
    }
        
    public static PageFactory screenFactory() {
        return Registry.get(PageFactory.class);
    }

    @Override
    public void onTap() {
         Screen.show(linkFactory.create(),screenFactory());
    }
}
