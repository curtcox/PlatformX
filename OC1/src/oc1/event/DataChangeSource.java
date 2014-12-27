package oc1.event;

import com.codename1.ui.events.DataChangedListener;

public interface DataChangeSource {
    void addDataChangedListener(DataChangedListener listener);
    void removeDataChangedListener(DataChangedListener listener);
}
