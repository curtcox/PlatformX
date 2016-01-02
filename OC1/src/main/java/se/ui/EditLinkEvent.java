package se.ui;

import se.events.Events;
import x.page.PageLink;
import x.uiwidget.XComponent;

public final class EditLinkEvent
    implements Events.Event
{
    public final PageLink link;
    public final XComponent layout;

    public EditLinkEvent(PageLink link, XComponent layout) {
        this.link = link;
        this.layout = layout;
    }
}
