package se.ui;

import common.page.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;

public final class EditLinkEvent
    implements Events.Event
{
    public final ScreenLink link;
    public final UIComponent layout;

    public EditLinkEvent(ScreenLink link, UIComponent layout) {
        this.link = link;
        this.layout = layout;
    }
}
