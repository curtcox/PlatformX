package common.ui;

import common.page.PageLink;
import common.screen.Screen;
import common.uiwidget.UIButton;

/**
 * A button that follows a link when you tap it.
 */
public final class LinkButton
    extends UIButton
{
    private final PageLink.Factory linkFactory;

    public LinkButton(final String name, final PageLink.Factory linkFactory) {
        super(name);
        this.linkFactory = linkFactory;
    }
        
    @Override
    public void onTap() {
         Screen.show(linkFactory.create());
    }
}
