package se.ui;

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
