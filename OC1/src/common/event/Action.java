package common.event;

public final class Action {

    final Object source;

    public Action(Object source) {
        this.source = source;
    }

    public interface Listener {
        public void actionPerformed(Action action);
    }
}
