package common.page;

import common.Registry;
import common.ui.IDisplay;
import common.uiwidget.UIComponent;

public abstract class Page {

    public final ScreenLink link;
    public final String title;

    public Page(ScreenLink link) {
        this.link = link;
        this.title = link.title();
    }

    /**
     * This is called whenever the screen is shown.
     * Override it in order to update any screen state that might have
     * changed since the last showing.
     */
    public void refresh() {}

    public abstract UIComponent layoutForPortrait();

    public UIComponent layoutForLandscape() {
        return layoutForPortrait();
    }

    public final boolean isPortrait() {
        return Registry.get(IDisplay.class).isPortrait();
    }


}
