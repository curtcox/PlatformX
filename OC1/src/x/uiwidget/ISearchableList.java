package x.uiwidget;

import x.event.Action;
import x.uilist.ListCellConfigurer;

import java.util.List;

/**
 * A graphical list of items that can be searched.
 * @param <T>
 */
public interface ISearchableList<T> {

    interface Factory {
        ISearchableList from(List items, UIComponent action, ListCellConfigurer configurer);
    }

    void onSelected(Action.Listener actionListener);
    Object getComponent();
    T getSelected();
}
