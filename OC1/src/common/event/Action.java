package common.event;

public final class Action {

    public interface Listener {
        public void actionPerformed(Action action);
    }
}
