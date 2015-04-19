package se.ui;

import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import se.events.Events;
import se.util.TaggedValue;

public final class EditTaggedValueEvent
    implements Events.Event
{
    public final TaggedValue taggedValue;

    public EditTaggedValueEvent(TaggedValue taggedValue) {
        this.taggedValue = taggedValue;
    }
}
