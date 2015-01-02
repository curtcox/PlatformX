package c1.ui;

import common.Registry;
import common.ui.UIButton;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

/**
 * A button that follows a link when you tap it.
 * @author Curt
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
         screenFactory().create(linkFactory.create()).show();
    }
}
