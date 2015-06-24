package se.ui;

import se.events.Events;
import x.page.PageLink;
import x.uiwidget.UIComponent;

public final class EditLinkEvent
    implements Events.Event
{
    public final PageLink link;
    public final UIComponent layout;

    public EditLinkEvent(PageLink link, UIComponent layout) {
        this.link = link;
        this.layout = layout;
    }
}
