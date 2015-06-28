package x.ui;

import x.page.PageLink;
import x.screen.Screen;
import x.uiwidget.XButton;

/**
 * A button that follows a link when you tap it.
 */
public final class LinkButton
    extends XButton
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
