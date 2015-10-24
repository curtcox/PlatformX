package se.ui;

import se.events.Events;
import se.util.TaggedValue;
import x.page.Page;
import x.uiwidget.XComponent;

public final class EditTaggedValueEvent
    implements Events.Event
{
    public final TaggedValue taggedValue;
    public final Page page;
    public final XComponent layout;

    public EditTaggedValueEvent(TaggedValue taggedValue, Page page, XComponent layout) {
        this.taggedValue = taggedValue;
        this.page = page;
        this.layout = layout;
    }
}
