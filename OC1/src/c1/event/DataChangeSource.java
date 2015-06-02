package c1.event;

import common.event.Change;

public interface DataChangeSource {
    void addDataChangedListener(Change.Listener listener);
    void removeDataChangedListener(Change.Listener listener);
}
