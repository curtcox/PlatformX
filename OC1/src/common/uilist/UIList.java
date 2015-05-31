package common.uilist;

import common.event.Action;

/**
 * For displaying a list of items.  Use a factory to create one.
 */
public interface UIList {

    interface Factory {
        UIList of(IListModel model,ListCellConfigurer configurer);
    }

    void addActionListener(Action.Listener listener);
    int getSelectedIndex();
}
