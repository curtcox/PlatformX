package se.events;

public final class SimpleListener
    implements Events.Listener
{
    @Override
    public void onEvent(Events.Event event) {

    }

    public Events.Event getLast() {
        return null;
    }
}
