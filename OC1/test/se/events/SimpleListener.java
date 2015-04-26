package se.events;

public final class SimpleListener
    implements Events.Listener
{
    private Events.Event event;

    @Override
    public void onEvent(Events.Event event) {
        this.event = event;
    }

    public Events.Event getLast() {
        return event;
    }
}
