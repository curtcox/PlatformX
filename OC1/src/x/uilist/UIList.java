package x.uilist;

import x.event.Action;

/**
 * For displaying a list of items.  Use a factory to create one.
 */
public interface UIList {
    void addActionListener(Action.Listener listener);
    int getSelectedIndex();
}
