package common.ui;

import common.Registry;
import common.screen.Screen;
import common.uiwidget.UIButton;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

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
        
    public static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }

    @Override
    public void onTap() {
         Screen.show(linkFactory.create(),screenFactory());
    }
}
