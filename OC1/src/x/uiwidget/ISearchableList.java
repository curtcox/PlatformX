package x.uiwidget;

import x.event.Action;
import x.uilist.ListCellConfigurer;

import java.util.List;

public interface ISearchableList<T> {

    interface Factory {
        ISearchableList from(List items, UIComponent action, ListCellConfigurer configurer);
    }

    void onSelected(Action.Listener actionListener);
    Object getComponent();
    T getSelected();
}
