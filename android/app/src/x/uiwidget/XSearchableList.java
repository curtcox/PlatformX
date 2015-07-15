package x.uiwidget;

import x.event.Action;
import x.uilist.IXListCell;

import java.util.List;

/**
 * A graphical list of items that can be searched.
 * @param <T>
 */
public interface XSearchableList<T> {

    interface Factory {
        XSearchableList from(List items, XComponent action, IXListCell.ConfigProducer configurer);
    }

    void onSelected(Action.Listener actionListener);
    Object getComponent();
    T getSelected();
}
