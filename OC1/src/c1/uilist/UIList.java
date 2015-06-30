package c1.uilist;

import x.event.Action;

/**
 * For displaying a list of items.  Use a factory to create one.
 * This interface exists, so that different CodenameOne list
 * implementations can easily be switched between.
 */
interface UIList {
    void addActionListener(Action.Listener listener);
    int getSelectedIndex();
}
