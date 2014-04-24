package com.mycompany.myapp.event;

import com.codename1.ui.events.DataChangedListener;

/**
 *
 * @author Curt
 */
public interface DataChangeSource {
    void addDataChangedListener(DataChangedListener listener);
    void removeDataChangedListener(DataChangedListener listener);
}
