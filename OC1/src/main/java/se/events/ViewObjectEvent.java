package se.events;

public final class ViewObjectEvent
    implements Events.Event
{
    public final Object object;

    public ViewObjectEvent(Object object) {
        this.object = object;
    }
}
